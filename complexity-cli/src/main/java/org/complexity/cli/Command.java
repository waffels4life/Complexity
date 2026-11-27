package org.complexity.cli;

import java.util.Objects;

public final class Command {

    private final Commands command;
    private final String[] args;

    public Command(Commands command, String[] args) {
        this.command = Objects.requireNonNull(command, "command cannot be null");
        this.args = Objects.requireNonNull(args, "args cannot be null").clone();
    }

    public Commands getCommand() {
        return command;
    }

    public String[] getArgs() {
        return args.clone();
    }
}