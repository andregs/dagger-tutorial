package com.example.tutorial.command.user;

import javax.inject.Inject;
import java.math.BigDecimal;

@PerSession
final class WithdrawalLimiter {
    private BigDecimal remainingWithdrawalLimit;

    @Inject
    public WithdrawalLimiter(@MaximumWithdrawal BigDecimal maximumWithdrawal) {
        this.remainingWithdrawalLimit = maximumWithdrawal;
    }

    void recordDeposit(BigDecimal amount) {
        remainingWithdrawalLimit = remainingWithdrawalLimit.add(amount);
    }

    void recordWithdrawal(BigDecimal amount) {
        remainingWithdrawalLimit = remainingWithdrawalLimit.subtract(amount);
    }

    public BigDecimal remainingWithdrawalLimit() {
        return remainingWithdrawalLimit;
    }

}
