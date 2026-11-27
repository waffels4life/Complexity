package org.complexity.cli.exception;

import java.util.Objects;

public class CliException extends RuntimeException {

    private final ExceptionType exceptionType;

    public CliException(ExceptionType exceptionType, String message) {
        super(message);
        this.exceptionType = Objects.requireNonNull(exceptionType);
    }

    public CliException(ExceptionType exceptionType,
                        String message,
                        Throwable cause) {
        super(message, cause);
        this.exceptionType = Objects.requireNonNull(exceptionType);
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }
}
