package com.sedatbsp.asu.domain.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UrlBusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String key;
}
