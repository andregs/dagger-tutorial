package com.example.tutorial;

import com.example.tutorial.Database.Account;

import javax.inject.Inject;
import java.math.BigDecimal;

final class WithdrawCommand extends BigDecimalCommand {
    private final Outputter outputter;
    private final Account account;
    private final BigDecimal minimumBalance;
    private final BigDecimal maximumWithdrawal;

    @Inject
    public WithdrawCommand(Outputter outputter,
                           Account account,
                           @MinimumBalance BigDecimal minimumBalance,
                           @MaximumWithdrawal BigDecimal maximumWithdrawal) {
        super(outputter);
        this.outputter = outputter;
        this.account = account;
        this.minimumBalance = minimumBalance;
        this.maximumWithdrawal = maximumWithdrawal;
    }

    @Override
    protected void handleAmount(BigDecimal amount) {
        if (amount.compareTo(maximumWithdrawal) > 0) {
            outputter.output(String.format("you cannot withdraw more than %s", maximumWithdrawal));
            return;
        }

        BigDecimal newBalance = account.balance().subtract(amount);
        if (newBalance.compareTo(minimumBalance) < 0) {
            String template = "You cannot withdraw %s. Your balance is %s and the minimum balance is %s.";
            outputter.output(String.format(template, amount, account.balance(), minimumBalance));
        } else {
            account.withdraw(amount);
            outputter.output("your new balance is: " + account.balance());
        }
    }
}
