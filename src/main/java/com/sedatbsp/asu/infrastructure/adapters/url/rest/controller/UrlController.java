package com.sedatbsp.asu.infrastructure.adapters.url.rest.controller;

import com.sedatbsp.asu.domain.url.facade.UrlFacade;
import com.sedatbsp.asu.infrastructure.adapters.url.rest.dto.request.UrlCreateRequest;
import com.sedatbsp.asu.infrastructure.adapters.url.rest.dto.response.UrlResponse;
import com.sedatbsp.asu.infrastructure.common.annotation.SecureApiController;
import com.sedatbsp.asu.infrastructure.common.rest.BaseController;
import com.sedatbsp.asu.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RequiredArgsConstructor
@SecureApiController
public class UrlController extends BaseController {

    private final UrlFacade urlFacade;

    @PostMapping("url")
    public Response<?> createUrl(@RequestBody UrlCreateRequest urlCreateRequest) {
        var url = urlFacade.createUrl(UrlCreateRequest.toModel(urlCreateRequest));
        return respond(UrlResponse.fromModel(url));
    }

    @GetMapping("{shortenedUrl}")
    public Response<?> redirectToUrl(@PathVariable String shortenedUrl, HttpServletResponse response) {
        var url = urlFacade.redirectToUrl(shortenedUrl, response);
        return null;
    }

    @GetMapping("urls")
    public Response<?> getUrls() {
        var urlList = urlFacade.getUrls();
        return respond(UrlResponse.fromListOfModel(urlList));
    }

    @GetMapping("url")
    public Response<?> getUrlById(@RequestParam("id") Long id) {
        var url = urlFacade.getUrlById(id);
        return respond(UrlResponse.fromModel(url));
    }

    @DeleteMapping("url")
    public Response<?> deleteUrl(@RequestParam("id") Long id) {
        var url = urlFacade.deleteUrl(id);
        return respond(UrlResponse.fromModel(url));
    }

}
