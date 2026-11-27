
<p align="center">
  <img src="assets/complexity-logo.png" alt="Complexity Logo" width="600">
</p>

<h1 align="center">Complexity</h1>

<p align="center">
A sorting algorithm advisor.
Give it a list, tell it what you care about, and it tells you which sorting algorithm to use — and why.
</p>

---

## The Problem

Choosing a sorting algorithm is usually either blind — "just use QuickSort" — or overkill, requiring research papers to sort a handful of integers.

Complexity sits in the middle.

It analyzes your data, considers your preferences, evaluates available algorithms, and recommends a suitable one with an explanation.

## How It Works

```text
Your Input
    |
    v
ArrayAnalyzer
    |  size, sortedness, uniqueness, type
    v
ScoringEngine
    |  evaluates algorithms using data + preferences
    v
Recommendation
    |  algorithm + score + reasons
    v
Sorting (optional)
```

## What Gets Analyzed

| Property | Description |
|---|---|
| Size | Number of elements |
| Sortedness | How close the input is to being sorted |
| Uniqueness | Number of distinct values |
| Type | Input data type and applicable algorithms |

## What You Can Configure

| Preference | Description |
|---|---|
| Speed over Memory | Prioritize execution speed |
| Stable | Prefer stable sorting algorithms |
| Memory Constrained | Prefer algorithms with lower memory requirements |

---

## Modules

Complexity is organized as a Maven multi-module project.

```text
complexity/
├── complexity-core/
│   └── Sorting and algorithm analysis library
│
└── complexity-cli/
    └── Command-line interface
```

### Complexity Core

The core module provides the Java API for analyzing and sorting collections.

### Complexity CLI

The CLI provides commands for sorting values, analyzing input, and displaying usage information.

---

## Quick Start — Java API

```java
Complexity<Integer> complexity = new Complexity.Builder<>(
        List.of(3, 1, 4, 1, 5, 9, 2, 6))
        .speedOverMemory(true)
        .needsStable(false)
        .memoryConstrained(false)
        .build();

AlgorithmScore result = complexity.analyze();

System.out.println(result.getAlgorithm());
System.out.println(result.getScore());
System.out.println(result.getReason());
```

### Sort directly

```java
List<Integer> sorted = complexity.sort(Algorithm.MERGESORT);

System.out.println(sorted);
```

### Analyze, then sort

```java
AlgorithmScore result = complexity.analyze();

List<Integer> sorted = complexity.sort(result.getAlgorithm());

System.out.println(sorted);
```

---

## CLI

Complexity CLI currently supports three commands:

| Command | Description |
|---|---|
| `sort` | Sort values using a selected algorithm |
| `analyze` | Analyze values and recommend an algorithm |
| `help` | Display command usage |

### Usage

```text
cmp <command> [options]
```

### Options

| Option | Description |
|---|---|
| `--values <v1,v2,...>` | Comma-separated integer values |
| `--algorithm <name>` | Sorting algorithm to use |
| `--stable` | Prefer stable algorithms |
| `--speed-over-memory` | Prioritize speed over memory |
| `--memory-constrained` | Prefer lower memory usage |

### Examples

Analyze a collection:

```bash
cmp analyze --values 8,3,5,1,9,2
```

Sort using a specific algorithm:

```bash
cmp sort --values 8,3,5,1 --algorithm INSERTION_SORT
```

Sort with preferences:

```bash
cmp sort --values 8,3,5,1 --algorithm MERGESORT --stable
```

Display help:

```bash
cmp help
```

The `cmp` command is the planned CLI executable name. Until the launcher is available, run the CLI through the Java application entry point.

---

## Algorithms

| Algorithm | Time Complexity | Space Complexity | Stable |
|---|---|---|---|
| QuickSort | O(n log n) average | O(log n) average | No |
| MergeSort | O(n log n) | O(n) | Yes |
| TimSort | O(n log n) | O(n) | Yes |
| HeapSort | O(n log n) | O(1) | No |
| Insertion Sort | O(n²) | O(1) | Yes |
| Counting Sort | O(n + k) | O(k) | Yes |
| Radix Sort | O(nk) | O(n + k) | Yes |

*Complexities may vary depending on implementation and input characteristics.*

---

## Build

Build the project from the root directory:

```bash
mvn clean verify
```

This builds the modules and runs the test suite.

## Built With

- Java
- Maven
- JUnit 5

---

**v1.5.0** — works. future versions will make it better. that's the deal.
