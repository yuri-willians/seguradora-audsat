package com.audsat.seguradora.core.commons.exception;

import java.io.Serial;

public class EntityNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2238353793435188066L;

    public EntityNotFoundException(final String message) {
        super(message);
    }

}
