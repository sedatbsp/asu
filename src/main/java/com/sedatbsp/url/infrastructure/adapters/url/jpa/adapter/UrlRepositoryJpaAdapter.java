package com.sedatbsp.url.infrastructure.adapters.url.jpa.adapter;

import com.sedatbsp.url.domain.url.command.UrlCreate;
import com.sedatbsp.url.domain.url.model.Url;
import com.sedatbsp.url.domain.url.port.UrlRepository;
import com.sedatbsp.url.infrastructure.adapters.url.jpa.entity.UrlEntity;
import com.sedatbsp.url.infrastructure.adapters.url.jpa.repository.UrlJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        return urlJpaRepository.save(urlEntity).toModel();
    }

    private LocalDateTime getExpirationDate(LocalDateTime expirationDate) {
        if(expirationDate == null){
            return LocalDateTime.now().plusMonths(6);
        }
        return expirationDate;
    }
}
