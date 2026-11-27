package org.complexity.cli;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class CommandDispatcherTest {

    private final ByteArrayOutputStream output =
            new ByteArrayOutputStream();

    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    private String getOutput() {
        return output.toString(StandardCharsets.UTF_8);
    }

    @Test
    void shouldDispatchSortCommand() {
        Command command = new Command(
                Commands.SORT,
                new String[]{
                        "--values", "8,3,5,1",
                        "--algorithm", "INSERTION_SORT"
                }
        );

        CommandDispatcher dispatcher = new CommandDispatcher(command);
        dispatcher.dispatch();

        assertTrue(getOutput().contains("[1, 3, 5, 8]"));
    }

    @Test
    void shouldDispatchAnalyzeCommand() {
        Command command = new Command(
                Commands.ANALYZE,
                new String[]{"--values", "8,3,5,1"}
        );

        CommandDispatcher dispatcher = new CommandDispatcher(command);
        dispatcher.dispatch();

        String result = getOutput();

        assertTrue(result.contains("Algorithm:"));
        assertTrue(result.contains("Score:"));
        assertTrue(result.contains("Reasons:"));
    }

    @Test
    void shouldDispatchHelpCommand() {
        Command command = new Command(
                Commands.HELP,
                new String[0]
        );

        CommandDispatcher dispatcher = new CommandDispatcher(command);
        dispatcher.dispatch();

        String result = getOutput();

        assertTrue(result.contains("Usage:"));
        assertTrue(result.contains("Commands:"));
        assertTrue(result.contains("--values"));
        assertTrue(result.contains("--algorithm"));
    }

    @Test
    void shouldRejectSortCommandWithoutRequiredOptions() {
        Command command = new Command(
                Commands.SORT,
                new String[]{"--values", "8,3,5,1"}
        );

        CommandDispatcher dispatcher = new CommandDispatcher(command);

        assertThrows(
                IllegalArgumentException.class,
                dispatcher::dispatch
        );
    }
}