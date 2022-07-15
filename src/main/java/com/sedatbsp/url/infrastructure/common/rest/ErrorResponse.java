package com.sedatbsp.url.infrastructure.common.rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class ErrorResponse {

    private String errorCode;

    private String errorDescription;
}
