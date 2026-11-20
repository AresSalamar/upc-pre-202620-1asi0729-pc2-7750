package com.icecube.platform.u202417747.shared.application.result;

import java.util.Objects;

/**
 * Represents a typed success or failure outcome from the application layer.
 * This is a study substitute, not the teacher-provided shared Result.
 *
 * @param <T> successful value type
 * @author Kevin Salazar
 */
public sealed interface Result<T> permits Result.Success, Result.Failure {
    /** Success with the produced domain value. */
    record Success<T>(T value) implements Result<T> {
        public Success {
            Objects.requireNonNull(value, "A successful result requires a value");
        }
    }

    /** Failure with a stable localization key and category. */
    record Failure<T>(ErrorType type, String messageKey) implements Result<T> {
        public Failure {
            Objects.requireNonNull(type, "Failure type must not be null");
            Objects.requireNonNull(messageKey, "Failure message key must not be null");
        }
    }

    /** Types mapped by the HTTP boundary to response statuses. */
    enum ErrorType { INVALID, CONFLICT, INTERNAL }

    /** @return a successful outcome containing the supplied value */
    static <T> Result<T> success(T value) { return new Success<>(value); }

    /** @return a failure carrying the type and translation key */
    static <T> Result<T> failure(ErrorType type, String messageKey) {
        return new Failure<>(type, messageKey);
    }
}
