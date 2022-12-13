package com.sedatbsp.asu.infrastructure.adapters.url.jpa.repository;

import com.sedatbsp.asu.domain.common.model.Status;
import com.sedatbsp.asu.infrastructure.adapters.url.jpa.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlJpaRepository extends JpaRepository<UrlEntity,Long> {

    Optional<UrlEntity> findByShortenedUrlAndStatus(String shortenedUrl, Status status);

}
