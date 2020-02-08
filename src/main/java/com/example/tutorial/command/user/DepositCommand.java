package com.example.tutorial.command.user;

import com.example.tutorial.shared.Database.Account;
import com.example.tutorial.shared.Outputter;

import javax.inject.Inject;
import java.math.BigDecimal;

final class DepositCommand extends BigDecimalCommand {
    private final Account account;
    private final Outputter outputter;
    private final WithdrawalLimiter withdrawalLimiter;

    @Inject
    DepositCommand(Account account, Outputter outputter, WithdrawalLimiter withdrawalLimiter) {
        super(outputter);
        this.account = account;
        this.outputter = outputter;
        this.withdrawalLimiter = withdrawalLimiter;
    }

    @Override
    public void handleAmount(BigDecimal amount) {
        account.deposit(amount);
        withdrawalLimiter.recordDeposit(amount);
        outputter.output(account.username() + " now has: " + account.balance());
    }

}

