package org.complexity.cli;

import org.complexity.cli.exception.CliException;
import org.complexity.cli.exception.ExceptionType;

import java.util.Arrays;
import java.util.Objects;

public final class CommandParser {

    private final String[] args;

    public CommandParser(String[] args) {
        Objects.requireNonNull(args, "args cannot be null");
        this.args = args.clone();
    }

    public Command parse() {

        if (this.args.length == 0)
            throw new CliException(
                    ExceptionType.COMMAND_EXCEPTION,
                    "Command is required. \n" +
                            "[TIP] Type 'cmp help' for command lines."
            );

        String commandName = Objects.requireNonNull(
                args[0],
                "Command cannot be null."
        );

        Commands command = switch (commandName) {
            case "analyze" -> Commands.ANALYZE;
            case "sort" -> Commands.SORT;
            case "help" -> Commands.HELP;
            default -> throw new CliException(
                    ExceptionType.COMMAND_EXCEPTION,
                    "Unknown command: " + commandName + "\n" +
                            "[TIP] Type 'cmp help' for command lines."
            );
        };

        String[] commandArgs = Arrays.copyOfRange(
                args,
                1,
                args.length
        );

        return new Command(command, commandArgs);
    }
}
