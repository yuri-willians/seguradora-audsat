package com.audsat.seguradora.application.configuration;

import com.audsat.seguradora.core.commons.dto.ResponseBase;
import com.audsat.seguradora.core.commons.exception.AttributeNotFoundException;
import com.audsat.seguradora.core.commons.exception.BusinessRuleException;
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

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseBase<Void> handle(final BusinessRuleException exception) {
        return ResponseBase.exception(exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(AttributeNotFoundException.class)
    public ResponseBase<Void> handle(final AttributeNotFoundException exception) {
        return ResponseBase.exception(exception.getMessage());
    }

}
