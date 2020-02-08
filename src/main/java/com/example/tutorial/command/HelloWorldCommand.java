package com.example.tutorial.command;

import com.example.tutorial.shared.Outputter;

import javax.inject.Inject;
import java.util.List;

final class HelloWorldCommand implements Command {

    private final Outputter outputter;

    @Inject
    public HelloWorldCommand(Outputter outputter) {
        this.outputter = outputter;
    }

    @Override
    public Result handleInput(List<String> input) {
        outputter.output("world!");
        return Result.handled();
    }
}
