package org.complexity.cli.exception;

public enum ExceptionType {

    COMMAND_EXCEPTION(2),
    INPUT_EXCEPTION(2),
    INTERNAL_EXCEPTION(1);

    private final int exitCode;

    ExceptionType(int exitCode) {
        this.exitCode = exitCode;
    }

    public int getExitCode() {
        return exitCode;
    }
}
