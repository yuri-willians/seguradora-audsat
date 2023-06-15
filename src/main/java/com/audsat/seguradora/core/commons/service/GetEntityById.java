package com.audsat.seguradora.core.commons.service;

import com.audsat.seguradora.core.commons.exception.EntityNotFoundException;

import java.util.Optional;
import java.util.function.Function;

public interface GetEntityById<T> {

    default T execute(final Long id) {
        return this.entityProvider().apply(id)
                .orElseThrow(() -> new EntityNotFoundException(this.notFoundMessageProvider()));
    }

    Function<Long, Optional<T>> entityProvider();

    String notFoundMessageProvider();

}
