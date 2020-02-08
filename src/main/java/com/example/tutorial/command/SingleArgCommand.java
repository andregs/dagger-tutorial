package com.example.tutorial.command;

import java.util.List;

/** Abstract command that accepts a single argument */
public abstract class SingleArgCommand implements Command {

    @Override
    public final Result handleInput(List<String> input) {
        return input.size() == 1 ? handleArg(input.get(0)) : Result.invalid();
    }

    protected abstract Result handleArg(String arg);
}
