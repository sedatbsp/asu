package com.sedatbsp.url.infrastructure.adapters.url.jpa.repository;

import com.sedatbsp.url.infrastructure.adapters.url.jpa.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlJpaRepository extends JpaRepository<UrlEntity,Long> {
}
