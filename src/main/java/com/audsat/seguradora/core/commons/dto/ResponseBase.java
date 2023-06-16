package com.audsat.seguradora.core.commons.dto;

public class ResponseBase<T> {

    private final boolean success;

    private final T data;

    private final String message;

    private ResponseBase(
            final boolean success,
            final T data,
            final String message
    ) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public static ResponseBase<Void> exception(final String message) {
        return new ResponseBase<>(
                false,
                null,
                message
        );
    }

    public static <T> ResponseBase<T> success(final T response) {
        return new ResponseBase<>(
                true,
                response,
                null
        );
    }

    public static <T> ResponseBase<T> success() {
        return new ResponseBase<>(
                true,
                null,
                null
        );
    }

    public boolean isSuccess() {
        return this.success;
    }

    public T getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }
}
