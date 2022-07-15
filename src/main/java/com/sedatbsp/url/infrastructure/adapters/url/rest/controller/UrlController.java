package com.sedatbsp.url.infrastructure.adapters.url.rest.controller;

import com.sedatbsp.url.domain.url.facade.UrlFacade;
import com.sedatbsp.url.infrastructure.adapters.url.rest.dto.request.UrlCreateRequest;
import com.sedatbsp.url.infrastructure.adapters.url.rest.dto.response.UrlResponse;
import com.sedatbsp.url.infrastructure.common.annotation.SecureApiController;
import com.sedatbsp.url.infrastructure.common.rest.BaseController;
import com.sedatbsp.url.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@SecureApiController
public class UrlController extends BaseController {

    private final UrlFacade urlFacade;

    @PostMapping("url")
    public Response<?> createUrl(@RequestBody UrlCreateRequest urlCreateRequest) {
        var url = urlFacade.createUrl(UrlCreateRequest.toModel(urlCreateRequest));
        return respond(UrlResponse.fromModel(url));
    }

}
