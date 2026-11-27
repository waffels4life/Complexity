package org.complexity.cli;

import java.util.Objects;

public record Command(Commands command, String[] args) {

    public Command(Commands command, String[] args) {
        this.command = Objects.requireNonNull(command, "command cannot be null");
        this.args = Objects.requireNonNull(args, "args cannot be null").clone();
    }

    @Override public String[] args() {
        return args.clone();
    }
}