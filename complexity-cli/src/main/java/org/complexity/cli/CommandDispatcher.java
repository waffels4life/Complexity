package org.complexity.cli;

import org.complexity.Complexity;
import org.complexity.model.Algorithm;

import java.util.List;
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
            case SORT -> {
                ParsingOption options = new ParsingOption(command);
                options.parse();

                Complexity<Integer> complexity = new Complexity.Builder<>(
                        options.values())
                        .speedOverMemory(options.isSpeedOverMemory())
                        .needsStable(options.isStable())
                        .memoryConstrained(options.isMemoryConstrained())
                        .build();

                List<Integer> sorted = complexity.sort(options.algorithm());
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