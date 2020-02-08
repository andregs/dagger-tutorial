package com.example.tutorial.command;

import com.example.tutorial.command.user.UserCommandsComponent;
import com.example.tutorial.shared.Database;
import com.example.tutorial.shared.Database.Account;
import com.example.tutorial.shared.Outputter;

import javax.inject.Inject;
import java.util.Optional;

public final class LoginCommand extends SingleArgCommand {
    private final Database database;
    private final Outputter outputter;
    private final UserCommandsComponent.Factory userCommandsRouterFactory;
    private final Optional<Account> account;

    @Inject
    LoginCommand(Database database,
                 Outputter outputter,
                 UserCommandsComponent.Factory userCommandsRouterFactory,
                 Optional<Account> account) {
        this.database = database;
        this.outputter = outputter;
        this.userCommandsRouterFactory = userCommandsRouterFactory;
        this.account = account;
    }

    @Override
    protected Result handleArg(String username) {
        if (account.isPresent()) {
            // when in UserCommandsComponent @Subcomponent this optional will be present
            outputter.output("ignored - already logged in.");
            return Result.handled();
        }

        // when in CommandProcessorComponent @Component the Optional will be absent
        Account account = database.getAccount(username);
        outputter.output(username + " is logged in with balance: " + account.balance());
        return Result.enterNestedCommandSet(
                userCommandsRouterFactory.create(account).router());
    }

}

