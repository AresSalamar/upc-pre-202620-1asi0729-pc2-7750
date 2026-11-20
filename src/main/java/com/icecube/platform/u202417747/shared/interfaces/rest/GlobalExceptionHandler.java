package com.icecube.platform.u202417747.shared.interfaces.rest;

import java.util.Locale;
import java.util.stream.Collectors;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Converts common request exceptions to translated JSON responses.
 * This is illustrative shared code, not the instructor's base-project handler.
 *
 * @author Kevin Salazar
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final MessageSource messages;

    public GlobalExceptionHandler(MessageSource messages) {
        this.messages = messages;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResource> handleValidation(MethodArgumentNotValidException ex, Locale locale) {
        String fields = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField())
                .distinct().sorted().collect(Collectors.joining(", "));
        return error(HttpStatus.BAD_REQUEST, "error.validation", locale, fields);
    }

    @ExceptionHandler({ IllegalArgumentException.class, HttpMessageNotReadableException.class })
    public ResponseEntity<ErrorResource> handleInvalid(Exception ex, Locale locale) {
        return error(HttpStatus.BAD_REQUEST, "error.invalidRequest", locale);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResource> handleIntegrity(DataIntegrityViolationException ex, Locale locale) {
        return error(HttpStatus.CONFLICT, "error.duplicateAlertCode", locale);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResource> handleUnexpected(Exception ex, Locale locale) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, "error.internal", locale);
    }

    private ResponseEntity<ErrorResource> error(HttpStatus status, String key, Locale locale, Object... args) {
        return ResponseEntity.status(status)
                .body(new ErrorResource(messages.getMessage(key, args, locale)));
    }
}
