package com.sedatbsp.asu.adapters;

import com.sedatbsp.asu.domain.common.exception.UrlBusinessException;
import com.sedatbsp.asu.domain.url.model.Url;
import com.sedatbsp.asu.infrastructure.adapters.url.jpa.adapter.UrlRepositoryJpaAdapter;
import com.sedatbsp.asu.infrastructure.adapters.url.jpa.entity.UrlEntity;
import com.sedatbsp.asu.infrastructure.adapters.url.jpa.repository.UrlJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;


public class UrlRepositoryJpaAdapterTest {

    @InjectMocks
    private UrlRepositoryJpaAdapter urlRepositoryJpaAdapter;

    @Mock
    private UrlJpaRepository urlJpaRepository;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test getUrlById called urlRepositoryJpaAdapter")
    void should_get_url_by_id() {
        Long id = 1L;

        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setUrl("www.google.com");
        urlEntity.setShortenedUrl("ASUASUGG");

        Mockito.when(urlJpaRepository.findById(id)).thenReturn(Optional.of(urlEntity));

        urlRepositoryJpaAdapter.getUrlById(id);

        verify(urlJpaRepository).findById(id);

    }

    @Test
    @DisplayName("Test getUrl throws an exception")
    void should_throw_exception_get_url() {
        Long id = 1L;

        Url url = new Url();
        url.setUrl("www.google.com");
        url.setShortenedUrl("ASUASUGG");

        Mockito.when(urlJpaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UrlBusinessException.class, () -> {
            urlRepositoryJpaAdapter.getUrlById(id);
        });

    }

    @Test
    @DisplayName("Test get urls as list")
    void should_return_urls_as_list() {
        urlRepositoryJpaAdapter.getUrls();

        verify(urlJpaRepository).findAll();
    }

}
