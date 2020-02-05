package com.example.tutorial;

import javax.inject.Inject;
import java.util.List;

final class HelloWorldCommand implements Command {

    private final Outputter outputter;

    @Inject
    public HelloWorldCommand(Outputter outputter) {
        this.outputter = outputter;
    }

    @Override
    public Status handleInput(List<String> input) {
        outputter.output("world!");
        return Status.HANDLED;
    }
}
