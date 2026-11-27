package org.complexity.cli;

import org.complexity.cli.exception.CliException;
import org.complexity.model.Algorithm;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParsingOptionTest {

    @Test
    void shouldParseValues() {
        Command command = new Command(
                Commands.SORT,
                new String[]{
                        "--values", "8,3,5,1",
                        "--algorithm", "QUICKSORT"
                }
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
                new String[]{
                        "--values", "8,3,5,1",
                        "--algorithm", "QUICKSORT"
                }
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
                        "--memory-constrained",
                        "--values", "8,3,5,1",
                        "--algorithm", "QUICKSORT"
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
                CliException.class,
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
                CliException.class,
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
                CliException.class,
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
                CliException.class,
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
                CliException.class,
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
                CliException.class,
                options::parse
        );
    }

    @Test
    void shouldRejectSortWithoutValues() {
        Command command = new Command(
                Commands.SORT,
                new String[]{"--algorithm", "QUICKSORT"}
        );

        ParsingOption options = new ParsingOption(command);

        assertThrows(
                CliException.class,
                options::parse
        );
    }

    @Test
    void shouldRejectSortWithoutAlgorithm() {
        Command command = new Command(
                Commands.SORT,
                new String[]{"--values", "8,3,5,1"}
        );

        ParsingOption options = new ParsingOption(command);

        assertThrows(
                CliException.class,
                options::parse
        );
    }

    @Test
    void shouldRejectAnalyzeWithoutValues() {
        Command command = new Command(
                Commands.ANALYZE,
                new String[]{"--stable"}
        );

        ParsingOption options = new ParsingOption(command);

        assertThrows(
                CliException.class,
                options::parse
        );
    }

    @Test
    void shouldAllowAnalyzeWithoutAlgorithm() {
        Command command = new Command(
                Commands.ANALYZE,
                new String[]{"--values", "8,3,5,1"}
        );

        ParsingOption options = new ParsingOption(command);

        assertDoesNotThrow(options::parse);

        assertNull(options.algorithm());
    }

    @Test
    void shouldAllowHelpWithoutOptions() {
        Command command = new Command(
                Commands.HELP,
                new String[]{}
        );

        ParsingOption options = new ParsingOption(command);

        assertDoesNotThrow(options::parse);
    }

    @Test
    void shouldRejectSortWhenValuesAreMissing() {
        Command command = new Command(
                Commands.SORT,
                new String[]{"--algorithm", "QUICKSORT"}
        );

        ParsingOption options = new ParsingOption(command);

        CliException exception = assertThrows(
                CliException.class,
                options::parse
        );

        assertEquals(
                "Missing required option: --values",
                exception.getMessage()
        );
    }

    @Test
    void shouldRejectSortWhenAlgorithmIsMissing() {
        Command command = new Command(
                Commands.SORT,
                new String[]{"--values", "2,3,5,3,2,3,1"}
        );

        ParsingOption option = new ParsingOption(command);

        CliException exception = assertThrows(
                CliException.class,
                option::parse
        );

        assertEquals(
                "Missing required option: --algorithm",
                exception.getMessage()
        );
    }

    @Test
    void shouldRejectAnalyzeWhenValuesAreMissing() {
        Command command = new Command(
                Commands.ANALYZE,
                new String[]{}
        );

        ParsingOption option = new ParsingOption(command);

        CliException exception = assertThrows(
                CliException.class,
                option::parse
        );

        assertEquals(
                "Missing required option: --values",
                exception.getMessage()
        );
    }

    @Test
    void shouldRejectValuesOptionWithoutValue() {
        Command command = new Command(
                Commands.SORT,
                new String[]{"--values"}
        );

        ParsingOption option = new ParsingOption(command);

        CliException exception = assertThrows(
                CliException.class,
                option::parse
        );

        assertEquals(
                "Missing value for --values",
                exception.getMessage()
        );
    }

    @Test
    void shouldRejectEmptyValues() {
        Command command = new Command(
                Commands.ANALYZE,
                new String[]{"--values", ""}
        );

        ParsingOption option = new ParsingOption(command);

        CliException exception = assertThrows(
                CliException.class,
                option::parse
        );

        assertEquals(
                "Values cannot be empty.",
                exception.getMessage()
        );
    }

    @Test
    void shouldRejectNonNumericValue() {
        Command command = new Command(
                Commands.ANALYZE,
                new String[]{"--values", "1,abc,3"}
        );

        ParsingOption option = new ParsingOption(command);

        CliException exception = assertThrows(
                CliException.class,
                option::parse
        );

        assertEquals(
                "Invalid integer value: abc",
                exception.getMessage()
        );
    }

    @Test
    void shouldRejectIntegerOutsideIntRange() {
        Command command = new Command(
                Commands.ANALYZE,
                new String[]{"--values", "999999999999"}
        );

        ParsingOption option = new ParsingOption(command);

        CliException exception = assertThrows(
                CliException.class,
                option::parse
        );

        assertEquals(
                "Invalid integer value: 999999999999",
                exception.getMessage()
        );
    }

    @Test
    void shouldRejectAlgorithmOptionWithoutValue() {
        Command command = new Command(
                Commands.SORT,
                new String[]{"--values", "1,2,3", "--algorithm"}
        );

        ParsingOption option = new ParsingOption(command);

        CliException exception = assertThrows(
                CliException.class,
                option::parse
        );

        assertEquals(
                "Missing value for --algorithm",
                exception.getMessage()
        );
    }

    @Test
    void shouldRejectTrailingCommaInValues() {
        Command command = new Command(
                Commands.ANALYZE,
                new String[]{"--values", "1,2,3,"}
        );

        ParsingOption option = new ParsingOption(command);

        assertThrows(
                CliException.class,
                option::parse
        );
    }

    @Test
    void shouldRejectEmptyElementBetweenValues() {
        Command command = new Command(
                Commands.ANALYZE,
                new String[]{"--values", "1,,3"}
        );

        ParsingOption option = new ParsingOption(command);

        assertThrows(
                CliException.class,
                option::parse
        );
    }
}