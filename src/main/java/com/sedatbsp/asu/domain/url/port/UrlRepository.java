package com.sedatbsp.asu.domain.url.port;

import com.sedatbsp.asu.domain.url.command.UrlCreate;
import com.sedatbsp.asu.domain.url.model.Url;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UrlRepository {
    Url save(UrlCreate urlCreate);

    Url redirectToUrl(String shortenedUrl, HttpServletResponse response);

    List<Url> getUrls();

    Url getUrlById(Long id);

    Url deleteUrl(Long id);
}
