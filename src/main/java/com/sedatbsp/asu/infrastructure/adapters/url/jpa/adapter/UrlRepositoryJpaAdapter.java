package com.sedatbsp.asu.infrastructure.adapters.url.jpa.adapter;

import com.sedatbsp.asu.domain.common.exception.UrlBusinessException;
import com.sedatbsp.asu.domain.common.model.Status;
import com.sedatbsp.asu.domain.url.command.UrlCreate;
import com.sedatbsp.asu.domain.url.model.Url;
import com.sedatbsp.asu.domain.url.port.UrlRepository;
import com.sedatbsp.asu.infrastructure.adapters.url.jpa.entity.UrlEntity;
import com.sedatbsp.asu.infrastructure.adapters.url.jpa.repository.UrlJpaRepository;
import com.sedatbsp.asu.infrastructure.common.constant.GenericMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    public List<Url> getUrls() {
        var urlEntityList = urlJpaRepository.findAll();

        return fromListOfModel(urlEntityList);
    }

    @Override
    public Url getUrlById(Long id) {
        var urlEntity = urlJpaRepository
                .findById(id)
                .orElseThrow(() -> new UrlBusinessException(GenericMessages.URL_NOT_FOUND));

        return toModel(urlEntity);
    }

    @Override
    public Url deleteUrl(Long id) {
        var urlEntity = urlJpaRepository
                .findById(id)
                .orElseThrow(() -> new UrlBusinessException(GenericMessages.URL_NOT_FOUND));

        urlEntity.setStatus(Status.DELETED);

        return toModel(urlJpaRepository.save(urlEntity));
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

    private List<Url> fromListOfModel(List<UrlEntity> urlEntityList) {
        return urlEntityList.stream().map(this::toModel).collect(Collectors.toList());
    }

}
