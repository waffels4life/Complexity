package org.complexity.cli;

import org.complexity.model.Algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public final class ParsingOption {

    private final Command command;

    private List<Integer> values = List.of();

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

        for (int i = 0; i < args.length; i++) {
            String arg = Objects.requireNonNull(
                    args[i],
                    "option cannot be null"
            );

            switch (arg) {
                case "--values" -> {
                    if (i + 1 >= args.length) {
                        throw new IllegalArgumentException(
                                "Missing value for --values"
                        );
                    }

                    values = parseValues(args[++i]);
                }

                case "--algorithm" -> {
                    if (i + 1 >= args.length) {
                        throw new IllegalArgumentException(
                                "Missing value for --algorithm"
                        );
                    }

                    algorithm = parseAlgorithm(args[++i]);
                }

                case "--stable" -> stable = true;

                case "--speed-over-memory" ->
                        speedOverMemory = true;

                case "--memory-constrained" ->
                        memoryConstrained = true;

                default -> throw new IllegalArgumentException(
                        "Unknown option: " + arg
                );
            }
        }
    }

    private List<Integer> parseValues(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Values cannot be empty."
            );
        }

        String[] elements = value.split(",", -1);

        List<Integer> parsedValues = new ArrayList<>();

        for (String element : elements) {
            try {
                parsedValues.add(Integer.parseInt(element.trim())
                );
            }
            catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                        "Invalid integer value: " + element,
                        e
                );
            }
        }

        return List.copyOf(parsedValues);
    }

    private Algorithm parseAlgorithm(String value) {
        try {
            return Algorithm.valueOf(value.toUpperCase(Locale.ROOT));
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "unknown algorithm: " + value,
                    e
            );
        }
    }

    public List<Integer> values() {
        return values;
    }

    public Algorithm algorithm() {
        return algorithm;
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