package com.sedatbsp.url.domain.url.port;

import com.sedatbsp.url.domain.url.command.UrlCreate;
import com.sedatbsp.url.domain.url.model.Url;

public interface UrlRepository {
    Url save(UrlCreate urlCreate);
}
