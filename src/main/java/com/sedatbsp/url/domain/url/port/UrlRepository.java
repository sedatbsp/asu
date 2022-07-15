package com.sedatbsp.url.domain.url.port;

import com.sedatbsp.url.domain.url.command.UrlCreate;
import com.sedatbsp.url.domain.url.model.Url;

import javax.servlet.http.HttpServletResponse;

public interface UrlRepository {
    Url save(UrlCreate urlCreate);

    Url redirectToUrl(String shortenedUrl, HttpServletResponse response);
}
