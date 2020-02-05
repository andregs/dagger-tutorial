package com.example.tutorial;

import com.example.tutorial.Database.Account;

import javax.inject.Inject;
import java.math.BigDecimal;

final class DepositCommand extends BigDecimalCommand {
    private final Account account;
    private final Outputter outputter;

    @Inject
    DepositCommand(Account account, Outputter outputter) {
        super(outputter);
        this.account = account;
        this.outputter = outputter;
    }

    @Override
    public void handleAmount(BigDecimal amount) {
        account.deposit(amount);
        outputter.output(account.username() + " now has: " + account.balance());
    }
}

