package org.complexity.cli;

import java.util.Objects;

public final class CommandDispatcher {

    private final Command command;
    public CommandDispatcher(Command command) {
        this.command = Objects.requireNonNull(command, "Command cannot be null");
    }

    public void dispatch() {
        switch (command.command()) {
            case Commands.ANALYZE -> {
                // TODO
            }
            case Commands.SORT -> {
                // TODO
            }
            case Commands.HELP -> printHelp();
            default -> throw new IllegalStateException(
                    "Unknown Command."
            );
        }
    }

    private void printHelp() {
        System.out.println("Help tools");
    }
}