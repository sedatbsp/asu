package com.sedatbsp.asu.domain.url.facade;

import com.sedatbsp.asu.domain.url.command.UrlCreate;
import com.sedatbsp.asu.domain.url.model.Url;
import com.sedatbsp.asu.domain.url.port.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    @Transactional
    public List<Url> getUrls() {
        return urlRepository.getUrls();
    }

    @Transactional
    public Url getUrlById(Long id) {
        return urlRepository.getUrlById(id);
    }

    public Url deleteUrl(Long id) {
        return urlRepository.deleteUrl(id);
    }
}
