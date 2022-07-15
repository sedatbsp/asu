package com.sedatbsp.url.infrastructure.adapters.url.jpa.entity;

import com.sedatbsp.url.domain.url.model.Url;
import com.sedatbsp.url.infrastructure.common.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "url")
public class UrlEntity extends AbstractEntity {

    @Column(name = "url",nullable = false)
    private String url;

    @Column(name = "shortened_url",unique = true, nullable = false)
    private String shortenedUrl;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    public Url toModel() {
        return Url
                .builder()
                .id(getId())
                .url(url)
                .shortenedUrl(shortenedUrl)
                .description(description)
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .status(getStatus())
                .build();
    }


}
