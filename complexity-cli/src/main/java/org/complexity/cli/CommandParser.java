package org.complexity.cli;

import java.util.Objects;

public final class CommandParser {
    private final String[] args;

    public CommandParser(String[] args) {
        Objects.requireNonNull(args, "args cannot be null");
        this.args = new String[args.length];

        System.arraycopy(args, 0, this.args, 0, args.length);
    }

    public Command parse() {

        if (this.args.length == 0)
            throw new IllegalArgumentException(
                    "Command is required."
            );

        if (this.args[0] == null)
            throw new IllegalArgumentException(
                    "Command cannot be null."
            );

        String command = this.args[0];
        switch (command) {
            case "analyze" -> {
                return new Command(Commands.ANALYZE, this.args);
            }
            case "sort" -> {
                return new Command(Commands.SORT, this.args);
            }
            case "help" -> {
                return new Command(Commands.HELP, this.args);
            }
            default -> throw new IllegalArgumentException(
                    "Unknown command: " + command + "\n" +
                            " [TIP] Type 'cmp help' for command lines."
            );
        }
    }
}
