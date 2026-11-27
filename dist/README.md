# Complexity

A command-line tool for analyzing and sorting data using different algorithms.

## Requirements

- Java 21 or later
- Java must be installed and available in your system PATH

Verify your Java installation:

```bash
java -version
```

## Usage

Run Complexity using `cmp.bat` from the command line.

### Show Help

```bash
cmp.bat help
```

### Sort Values

```bash
cmp.bat sort --values 3,4,1,2 --algorithm INSERTION_SORT
```

### Analyze Values

```bash
cmp.bat analyze --values 3,4,1,2
```

Complexity analyzes the input and recommends a suitable sorting algorithm based on the input and available constraints.

## Options

| Option | Description |
|---|---|
| `--values` | Comma-separated integer values |
| `--algorithm` | Sorting algorithm to use |
| `--stable` | Prefer stable sorting algorithms |
| `--speed-over-memory` | Prioritize speed over memory usage |
| `--memory-constrained` | Prefer algorithms with lower memory usage |

## Error Handling

Complexity reports invalid commands, missing options, and invalid input with an appropriate error message.

## License

See the project repository for license information.