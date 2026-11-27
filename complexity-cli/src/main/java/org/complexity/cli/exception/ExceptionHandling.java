package org.complexity.cli.exception;

import java.io.PrintStream;
import java.util.Objects;

public final class ExceptionHandling {
    private ExceptionHandling() {}

    public static int handle(Throwable throwable, PrintStream printStream) {
        Objects.requireNonNull(throwable, "Error cannot be null");
        Objects.requireNonNull(printStream, "PrintStream cannot be null");

        if (throwable instanceof CliException cliException) {
            printStream.println("Error: " + cliException.getMessage());
            return cliException.getExceptionType().getExitCode();
        }

        printStream.println("An unexpected error occurred");
        printStream.println("Please report this issue.");

        return ExceptionType.INTERNAL_EXCEPTION.getExitCode();
    }
}
