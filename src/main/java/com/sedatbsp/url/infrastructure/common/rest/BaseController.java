package com.sedatbsp.url.infrastructure.common.rest;

import java.util.List;

public class BaseController {

    public <T> Response<DataResponse<T>> respond(List<T> items) {
        return ResponseBuilder.build(items);
    }

    protected <T> Response<T> respond(T item) {
        return ResponseBuilder.build(item);
    }

    protected Response<?> respond(ErrorResponse errorResponse) {
        return ResponseBuilder.build(errorResponse);
    }

}
