package com.sedatbsp.asu.infrastructure.common.rest;

import com.sedatbsp.asu.domain.common.exception.UrlBusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler extends BaseController {

    private final MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UrlBusinessException.class)
    public Response<?> handleOtmApiBusinessException(UrlBusinessException otmBusinessException, Locale locale) {
        return createErrorResponseFromMessageSource(otmBusinessException.getKey(), locale);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WebExchangeBindException.class)
    public Response<?> handleRequestPropertyBindingError(WebExchangeBindException webExchangeBindException) {
        log.error(webExchangeBindException.getMessage());
        return respond(new ErrorResponse("400", webExchangeBindException.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<?> handleMethodArgumentNotValidationException(
            MethodArgumentNotValidException methodArgumentNotValidException) {
        log.error(methodArgumentNotValidException.getMessage());
        return respond(new ErrorResponse("400",
                methodArgumentNotValidException.getBindingResult().getFieldError().getDefaultMessage()));
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public Response<?> handleAccessDeniedException(AccessDeniedException accessDeniedException) {
        log.error(accessDeniedException.getMessage());
        return respond(new ErrorResponse("403", accessDeniedException.getMessage()));
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Response<?> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
        log.error(methodArgumentTypeMismatchException.getMessage());
        return respond(new ErrorResponse("422", methodArgumentTypeMismatchException.getMessage()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Response<?> handleException(Exception exception) {
        log.error(exception.getMessage());
        return respond(new ErrorResponse("500", exception.getMessage()));
    }

    private Response<?> createErrorResponseFromMessageSource(String key, Locale locale, String... args) {
        List<String> messageList = retrieveLocalizationMessage(key, locale, args);
        return respond(new ErrorResponse(messageList.get(0), messageList.get(1)));
    }

    private List<String> retrieveLocalizationMessage(String key, Locale locale, String... args) {
        String message = messageSource.getMessage(key, args, locale);
        return Pattern.compile(";").splitAsStream(message).collect(Collectors.toList());
    }

}
