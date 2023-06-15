package com.audsat.seguradora.core.commons.exception;

import java.io.Serial;

public class BusinessRuleException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2238353793435188066L;

    public BusinessRuleException(final String message) {
        super(message);
    }

}
