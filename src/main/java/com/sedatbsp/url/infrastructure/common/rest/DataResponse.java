package com.sedatbsp.url.infrastructure.common.rest;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DataResponse<T> {

    @Builder.Default
    private List<T> items = List.of();

}
