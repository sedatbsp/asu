package com.sedatbsp.asu.domain.url.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlCreate {

    private String url;

    private LocalDateTime expirationDate; // optional

    private String description;

}
