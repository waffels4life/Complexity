package org.complexity.cli;

import org.complexity.Complexity;
import org.complexity.scoring.AlgorithmScore;

import java.util.List;
import java.util.Objects;

public final class CommandDispatcher {

    private final Command command;
    public CommandDispatcher(Command command) {
        this.command = Objects.requireNonNull(command, "Command cannot be null");
    }

    public void dispatch() {
        switch (command.command()) {
            case ANALYZE -> {
                ParsingOption options = new ParsingOption(command);
                options.parse();

                Complexity<Integer> complexity = new Complexity.Builder<>(
                        options.values())
                        .speedOverMemory(options.isSpeedOverMemory())
                        .needsStable(options.isStable())
                        .memoryConstrained(options.isMemoryConstrained())
                        .build();

                AlgorithmScore score = complexity.analyze();

                System.out.println("Algorithm: " + score.getAlgorithm());
                System.out.println("Score: " + score.getScore());
                System.out.println();
                System.out.println("Reasons:");
                System.out.println(score.getReason());
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
                System.out.println(sorted);
            }
            case Commands.HELP -> printHelp();
            default -> throw new IllegalStateException(
                    "Unknown Command."
            );
        }
    }

    private void printHelp() {
        System.out.println(
                """
                Complexity CLI
                Analyze and sort integer collections.
                
                Usage:
                  cmp <command> [options]
                
                Commands:
                  sort       Sort values using a selected algorithm
                  analyze    Analyze values and recommend an algorithm
                  help       Show this help message
                
                Options:
                  --values <v1,v2,...>       Comma-separated integers
                  --algorithm <name>         Sorting algorithm
                  --stable                   Prefer stable algorithms
                  --speed-over-memory        Prioritize speed over memory
                  --memory-constrained       Limit memory usage
                
                Examples:
                  cmp sort --values 8,3,5,1 --algorithm INSERTION_SORT
                  cmp analyze --values 8,3,5,1
                """);
    }
}