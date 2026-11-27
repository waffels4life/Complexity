package org.complexity.cli;

public class ComplexityCli {
    public static void main(String[] args) {
        Command command = new CommandParser(args).parse();

        new CommandDispatcher(command).dispatch();
    }
}