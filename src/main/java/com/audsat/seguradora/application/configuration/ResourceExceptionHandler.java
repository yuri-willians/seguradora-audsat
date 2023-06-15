package com.audsat.seguradora.application.configuration;

import com.audsat.seguradora.core.commons.dto.ResponseBase;
import com.audsat.seguradora.core.commons.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseBase<Void> handle(final EntityNotFoundException exception) {
        return ResponseBase.exception(exception.getMessage());
    }

}
