package com.example.tutorial;

import javax.inject.Inject;
import java.util.List;

/** Abstract command that accepts a single argument */
abstract class SingleArgCommand implements Command {

    @Override
    public final Status handleInput(List<String> input) {
        return input.size() == 1 ? handleArg(input.get(0)) : Status.INVALID;
    }

    protected abstract Status handleArg(String arg);
}

final class LoginCommand extends SingleArgCommand {

    private final Outputter outputter;

    @Inject
    LoginCommand(Outputter outputter) {
        this.outputter = outputter;
    }

    @Override
    protected Status handleArg(String username) {
        outputter.output(username + " is logged in.");
        return Status.HANDLED;
    }

}
