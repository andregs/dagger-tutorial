package com.example.tutorial;

import com.example.tutorial.Database.Account;

import javax.inject.Inject;
import java.util.List;

final class LoginCommand extends SingleArgCommand {

    private final Database database;
    private final Outputter outputter;

    @Inject
    LoginCommand(Database database, Outputter outputter) {
        this.database = database;
        this.outputter = outputter;
        System.out.println("Creating a new " + this);
    }

    @Override
    protected Status handleArg(String username) {
        Account account = database.getAccount(username);
        outputter.output(username + " is logged in with balance: " + account.balance());
        return Status.HANDLED;
    }

}

/** Abstract command that accepts a single argument */
abstract class SingleArgCommand implements Command {

    @Override
    public final Status handleInput(List<String> input) {
        return input.size() == 1 ? handleArg(input.get(0)) : Status.INVALID;
    }

    protected abstract Status handleArg(String arg);
}
