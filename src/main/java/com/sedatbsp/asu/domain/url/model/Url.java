package com.sedatbsp.asu.domain.url.model;

import com.sedatbsp.asu.domain.common.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Url {

    private Long id;

    private String url;

    private String shortenedUrl;

    private LocalDateTime expirationDate; // optional

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Status status;

}
