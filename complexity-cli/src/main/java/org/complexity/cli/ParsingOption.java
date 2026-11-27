package org.complexity.cli;

import org.complexity.model.Algorithm;

import java.util.List;
import java.util.Objects;

public final class ParsingOption {
    private final Command command;

    private List<Integer> values;
    private Algorithm algorithm;
    private boolean stable;
    private boolean speedOverMemory;
    private boolean memoryConstrained;

    public ParsingOption(Command command) {
        this.command = Objects.requireNonNull(
                command,
                "command cannot be null"
        );
    }

    public void parse() {
        String[] args = command.args();

        for (String arg : args) {
            Objects.requireNonNull(arg, "option cannot be null");

            switch (arg) {
                case "--stable" -> stable = true;
                case "--speed-over-memory" -> speedOverMemory = true;
                case "--memory-constrained" -> memoryConstrained = true;

                default -> throw new IllegalArgumentException(
                        "Unknown option: " + arg
                );
            }
        }
    }

    public boolean isStable() {
        return stable;
    }

    public boolean isSpeedOverMemory() {
        return speedOverMemory;
    }

    public boolean isMemoryConstrained() {
        return memoryConstrained;
    }
}