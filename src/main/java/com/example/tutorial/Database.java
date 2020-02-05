package com.example.tutorial;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Singleton // Dagger will create only one instance of Database per instance of our @Component
class Database {

    private final Map<String, Account> accounts = new HashMap<>();

    @Inject
    public Database() {
        System.out.println("Creating a new " + this);
    }

    Account getAccount(String username) {
        return accounts.computeIfAbsent(username, Account::new);
    }

    static final class Account {
        private final String username;
        private BigDecimal balance = BigDecimal.ZERO;

        Account(String username) {
            this.username = username;
        }

        public String username() {
            return username;
        }

        public BigDecimal balance() {
            return balance;
        }

        public void deposit(BigDecimal amount) {
            balance = balance.add(amount);
        }
    }
}
