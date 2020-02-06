package com.example.tutorial;

import javax.inject.Inject;
import java.util.List;

final class LogoutCommand implements Command {
    private final Outputter outputter;

    @Inject
    public LogoutCommand(Outputter outputter) {
        this.outputter = outputter;
    }

    @Override
    public Result handleInput(List<String> input) {
        if (!input.isEmpty()) {
            return Result.invalid();
        }
        outputter.output("Bye!");
        return input.isEmpty() ? Result.inputCompleted() : Result.invalid();
    }

}
