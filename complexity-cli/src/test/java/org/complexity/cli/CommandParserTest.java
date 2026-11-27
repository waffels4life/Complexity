package org.complexity.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandParserTest {

    @Test
    void shouldParseSortCommandAndPreserveArguments() {
        String[] args = {
                "sort",
                "--values", "8,3,5,1",
                "--algorithm", "QUICKSORT"
        };

        CommandParser parser = new CommandParser(args);
        Command command = parser.parse();

        assertEquals(Commands.SORT, command.command());
        assertArrayEquals(
                new String[]{
                        "--values", "8,3,5,1",
                        "--algorithm", "QUICKSORT"
                },
                command.args()
        );
    }

    @Test
    void shouldParseAnalyzeCommand() {
        String[] args = {
                "analyze",
                "--values", "8,3,5,1"
        };

        CommandParser parser = new CommandParser(args);
        Command command = parser.parse();

        assertEquals(Commands.ANALYZE, command.command());
        assertArrayEquals(
                new String[]{"--values", "8,3,5,1"},
                command.args()
        );
    }

    @Test
    void shouldParseHelpCommand() {
        String[] args = {"help"};

        CommandParser parser = new CommandParser(args);
        Command command = parser.parse();

        assertEquals(Commands.HELP, command.command());
        assertArrayEquals(new String[0], command.args());
    }

    @Test
    void shouldRejectUnknownCommand() {
        String[] args = {"unknown"};

        CommandParser parser = new CommandParser(args);

        assertThrows(
                IllegalArgumentException.class,
                parser::parse
        );
    }

    @Test
    void shouldRejectEmptyArguments() {
        String[] args = {};

        CommandParser parser = new CommandParser(args);

        assertThrows(
                IllegalArgumentException.class,
                parser::parse
        );
    }

    @Test
    void shouldRejectNullArguments() {
        assertThrows(
                NullPointerException.class,
                () -> new CommandParser(null)
        );
    }
}