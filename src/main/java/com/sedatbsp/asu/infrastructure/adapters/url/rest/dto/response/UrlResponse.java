package com.sedatbsp.asu.infrastructure.adapters.url.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.sedatbsp.asu.domain.common.model.Status;
import com.sedatbsp.asu.domain.url.model.Url;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlResponse {

    private Long id;

    private String url;

    private String shortenedUrl;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime expirationDate;

    private Status status;

    public static UrlResponse fromModel(Url url) {
        return UrlResponse
                .builder()
                .id(url.getId())
                .url(url.getUrl())
                .shortenedUrl(url.getShortenedUrl())
                .expirationDate(url.getExpirationDate())
                .status(url.getStatus())
                .build();
    }


    public static List<UrlResponse> fromListOfModel(List<Url> urls) {
        return urls.stream().map(UrlResponse::fromModel).collect(Collectors.toList());
    }

}
