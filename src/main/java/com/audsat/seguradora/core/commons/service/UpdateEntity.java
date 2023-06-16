package com.audsat.seguradora.core.commons.service;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public interface UpdateEntity {

    default <T> void maybeUpdate(
            final Consumer<T> consumer,
            final Supplier<T> supplier
    ) {
        final var supplierValue = supplier.get();
        if (Objects.isNull(supplierValue)) return;
        consumer.accept(supplierValue);
    }

    default <T> void maybeUpdateEntity(
            final Function<Long, T> entityProvider,
            final Consumer<T> consumer,
            final Supplier<Long> supplier
    ) {
        final var supplierValue = supplier.get();
        if (Objects.isNull(supplierValue)) return;
        final var entity = entityProvider.apply(supplierValue);
        consumer.accept(entity);
    }

}
