package com.sedatbsp.url.infrastructure.adapters.url.jpa.adapter;

import com.sedatbsp.url.domain.common.exception.UrlBusinessException;
import com.sedatbsp.url.domain.url.command.UrlCreate;
import com.sedatbsp.url.domain.url.model.Url;
import com.sedatbsp.url.domain.url.port.UrlRepository;
import com.sedatbsp.url.infrastructure.adapters.url.jpa.entity.UrlEntity;
import com.sedatbsp.url.infrastructure.adapters.url.jpa.repository.UrlJpaRepository;
import com.sedatbsp.url.infrastructure.common.constant.GenericMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UrlRepositoryJpaAdapter implements UrlRepository {

    private final UrlJpaRepository urlJpaRepository;

    @Override
    public Url save(UrlCreate urlCreate) {
        var urlEntity = new UrlEntity();
        urlEntity.setUrl(urlCreate.getUrl());
        urlEntity.setShortenedUrl(UUID.randomUUID().toString().substring(0,8));
        urlEntity.setDescription(urlCreate.getDescription());
        urlEntity.setExpirationDate(getExpirationDate(urlCreate.getExpirationDate()));

        return toModel(urlJpaRepository.save(urlEntity));
    }

    @Override
    public Url redirectToUrl(String shortenedUrl, HttpServletResponse response) {
        var url = urlJpaRepository
                .findByShortenedUrl(shortenedUrl)
                .orElseThrow(() -> new UrlBusinessException(GenericMessages.URL_NOT_FOUND));

        try {
            response.sendRedirect(url.getUrl());
        }
        catch (IOException ioException) {
            throw new UrlBusinessException("unexception.error");
        }
        return null;
    }

    private LocalDateTime getExpirationDate(LocalDateTime expirationDate) {
        if(expirationDate == null){
            return LocalDateTime.now().plusMonths(6);
        }
        return expirationDate;
    }

    private Url toModel(UrlEntity urlEntity) {
        return Url
                .builder()
                .id(urlEntity.getId())
                .url(urlEntity.getUrl())
                .shortenedUrl(urlEntity.getShortenedUrl())
                .description(urlEntity.getDescription())
                .expirationDate(urlEntity.getExpirationDate())
                .createdAt(urlEntity.getCreatedAt())
                .updatedAt(urlEntity.getUpdatedAt())
                .status(urlEntity.getStatus())
                .build();
    }

}
