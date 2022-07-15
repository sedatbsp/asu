package com.sedatbsp.url.domain.url.facade;

import com.sedatbsp.url.domain.url.command.UrlCreate;
import com.sedatbsp.url.domain.url.model.Url;
import com.sedatbsp.url.domain.url.port.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class UrlFacade {

    private final UrlRepository urlRepository;

    @Transactional
    public Url createUrl(UrlCreate urlCreate) {
        return urlRepository.save(urlCreate);
    }

    @Transactional
    public Url redirectToUrl(String shortenedUrl, HttpServletResponse response) {
        return urlRepository.redirectToUrl(shortenedUrl, response);
    }
}
