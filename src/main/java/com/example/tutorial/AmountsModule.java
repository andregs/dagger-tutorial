package com.example.tutorial;

import dagger.Module;
import dagger.Provides;

import java.math.BigDecimal;

@Module
interface AmountsModule {

    @Provides
    @MinimumBalance
    static BigDecimal minimumBalance() {
        return BigDecimal.ZERO;
    }

    @Provides
    @MaximumWithdrawal
    static BigDecimal maximumBalance() {
        return new BigDecimal(1000);
    }
}
