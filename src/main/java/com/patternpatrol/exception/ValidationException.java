package com.patternpatrol.exception;

public class ValidationException extends PatternPatrolException {

    public ValidationException(final String message) {
        super(message);
    }

    public ValidationException(final String message, final Exception cause) {
        super(message, cause);
    }
}
