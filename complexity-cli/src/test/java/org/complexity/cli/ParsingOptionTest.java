package org.complexity.cli;

import org.complexity.model.Algorithm;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParsingOptionTest {

    @Test
    void shouldParseValues() {
        Command command = new Command(
                Commands.SORT,
                new String[]{"--values", "8,3,5,1"}
        );

        ParsingOption options = new ParsingOption(command);
        options.parse();

        assertEquals(
                List.of(8, 3, 5, 1),
                options.values()
        );
    }

    @Test
    void shouldParseAlgorithm() {
        Command command = new Command(
                Commands.SORT,
                new String[]{"--algorithm", "QUICKSORT"}
        );

        ParsingOption options = new ParsingOption(command);
        options.parse();

        assertEquals(
                Algorithm.QUICKSORT,
                options.algorithm()
        );
    }

    @Test
    void shouldParseConfigurationFlags() {
        Command command = new Command(
                Commands.SORT,
                new String[]{
                        "--stable",
                        "--speed-over-memory",
                        "--memory-constrained"
                }
        );

        ParsingOption options = new ParsingOption(command);
        options.parse();

        assertTrue(options.isStable());
        assertTrue(options.isSpeedOverMemory());
        assertTrue(options.isMemoryConstrained());
    }

    @Test
    void shouldParseOptionsInAnyOrder() {
        Command command = new Command(
                Commands.SORT,
                new String[]{
                        "--stable",
                        "--algorithm", "QUICKSORT",
                        "--values", "8,3,5,1"
                }
        );

        ParsingOption options = new ParsingOption(command);
        options.parse();

        assertEquals(
                List.of(8, 3, 5, 1),
                options.values()
        );

        assertEquals(
                Algorithm.QUICKSORT,
                options.algorithm()
        );

        assertTrue(options.isStable());
    }

    @Test
    void shouldRejectInvalidInteger() {
        Command command = new Command(
                Commands.SORT,
                new String[]{"--values", "8,abc,5"}
        );

        ParsingOption options = new ParsingOption(command);

        assertThrows(
                IllegalArgumentException.class,
                options::parse
        );
    }

    @Test
    void shouldRejectEmptyValueElement() {
        Command command = new Command(
                Commands.SORT,
                new String[]{"--values", "8,,5"}
        );

        ParsingOption options = new ParsingOption(command);

        assertThrows(
                IllegalArgumentException.class,
                options::parse
        );
    }

    @Test
    void shouldRejectMissingValuesArgument() {
        Command command = new Command(
                Commands.SORT,
                new String[]{"--values"}
        );

        ParsingOption options = new ParsingOption(command);

        assertThrows(
                IllegalArgumentException.class,
                options::parse
        );
    }

    @Test
    void shouldRejectMissingAlgorithmArgument() {
        Command command = new Command(
                Commands.SORT,
                new String[]{"--algorithm"}
        );

        ParsingOption options = new ParsingOption(command);

        assertThrows(
                IllegalArgumentException.class,
                options::parse
        );
    }

    @Test
    void shouldRejectUnknownAlgorithm() {
        Command command = new Command(
                Commands.SORT,
                new String[]{"--algorithm", "MAGICSORT"}
        );

        ParsingOption options = new ParsingOption(command);

        assertThrows(
                IllegalArgumentException.class,
                options::parse
        );
    }

    @Test
    void shouldRejectUnknownOption() {
        Command command = new Command(
                Commands.SORT,
                new String[]{"--unknown"}
        );

        ParsingOption options = new ParsingOption(command);

        assertThrows(
                IllegalArgumentException.class,
                options::parse
        );
    }
}