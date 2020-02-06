package com.example.tutorial;

import com.example.tutorial.Database.Account;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

final class LoginCommand extends SingleArgCommand {
    private final Database database;
    private final Outputter outputter;
    private final UserCommandsRouter.Factory userCommandsRouterFactory;
    private final Optional<Account> account;

    @Inject
    LoginCommand(Database database,
                 Outputter outputter,
                 UserCommandsRouter.Factory userCommandsRouterFactory,
                 Optional<Account> account) {
        this.database = database;
        this.outputter = outputter;
        this.userCommandsRouterFactory = userCommandsRouterFactory;
        this.account = account;
    }

    @Override
    protected Result handleArg(String username) {
        if (account.isPresent()) {
            // when in UserCommandsRouter @Subcomponent this optional will be present
            outputter.output("ignored - already logged in.");
            return Result.handled();
        }

        // when in CommandProcessorFactory @Component the Optional will be absent
        Account account = database.getAccount(username);
        outputter.output(username + " is logged in with balance: " + account.balance());
        return Result.enterNestedCommandSet(
                userCommandsRouterFactory.create(account).router());
    }

}

/** Abstract command that accepts a single argument */
abstract class SingleArgCommand implements Command {

    @Override
    public final Result handleInput(List<String> input) {
        return input.size() == 1 ? handleArg(input.get(0)) : Result.invalid();
    }

    protected abstract Result handleArg(String arg);
}
