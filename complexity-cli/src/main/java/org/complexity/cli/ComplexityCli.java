
package org.complexity.cli;

import org.complexity.cli.exception.ExceptionHandling;

public class ComplexityCli {

    public static void main(String[] args) {
        int exitCode;

        try {
            Command command = new CommandParser(args).parse();
            new CommandDispatcher(command).dispatch();

            exitCode = 0;
        }
        catch (Exception exception) {
            exitCode = ExceptionHandling.handle(exception, System.err);
        }

        if (exitCode != 0) System.exit(exitCode);
    }
}
