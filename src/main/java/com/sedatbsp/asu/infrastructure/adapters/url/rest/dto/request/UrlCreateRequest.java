package com.sedatbsp.asu.infrastructure.adapters.url.rest.dto.request;

import com.sedatbsp.asu.domain.url.command.UrlCreate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlCreateRequest {

    @NotNull(message = "{url.constraint.url.NotNull.message}")
    private String url;

    private LocalDateTime expirationDate; // optional

    @NotNull(message = "{url.constraint.description.NotNull.message}")
    @Size(max = 1000, message = "{url.constraint.description.Size.message}")
    private String description;

    public static UrlCreate toModel(UrlCreateRequest urlCreateRequest) {
        return UrlCreate
                .builder()
                .url(urlCreateRequest.getUrl())
                .expirationDate(urlCreateRequest.getExpirationDate())
                .description(urlCreateRequest.getDescription())
                .build();
    }


}
