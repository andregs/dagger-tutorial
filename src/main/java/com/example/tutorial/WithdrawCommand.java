package com.example.tutorial;

import com.example.tutorial.Database.Account;

import javax.inject.Inject;
import java.math.BigDecimal;

final class WithdrawCommand extends BigDecimalCommand {
    private final Outputter outputter;
    private final Account account;
    private final BigDecimal minimumBalance;
    private final WithdrawalLimiter withdrawalLimiter;

    @Inject
    public WithdrawCommand(Outputter outputter,
                           Account account,
                           @MinimumBalance BigDecimal minimumBalance,
                           WithdrawalLimiter withdrawalLimiter) {
        super(outputter);
        this.outputter = outputter;
        this.account = account;
        this.minimumBalance = minimumBalance;
        this.withdrawalLimiter = withdrawalLimiter;
    }

    @Override
    protected void handleAmount(BigDecimal amount) {
        BigDecimal remainingWithdrawalLimit = withdrawalLimiter.remainingWithdrawalLimit();
        if (amount.compareTo(remainingWithdrawalLimit) > 0) {
            String template = "you may not withdraw %s; you may withdraw %s more in this session";
            outputter.output(String.format(template, amount, remainingWithdrawalLimit));
            return;
        }

        BigDecimal newBalance = account.balance().subtract(amount);
        if (newBalance.compareTo(minimumBalance) < 0) {
            String template = "You cannot withdraw %s. Your balance is %s and the minimum balance is %s.";
            outputter.output(String.format(template, amount, account.balance(), minimumBalance));
        } else {
            account.withdraw(amount);
            withdrawalLimiter.recordWithdrawal(amount);
            outputter.output("your new balance is: " + account.balance());
        }
    }
}
