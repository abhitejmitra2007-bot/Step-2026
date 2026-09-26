package com.gdb.domain;

import com.gdb.exceptions.*;

public class SavingsAccount extends AbstractAccount {
    private double minBalance;
    private double interestRate;

    public SavingsAccount(String accountNumber, String name, int age, double balance,
                          String status, String pin) {
        this(accountNumber, name, age, balance, status, pin, 1000.0, 4.0);
    }

    public SavingsAccount(String accountNumber, String name, int age, double balance,
                          String status, String pin, double minBalance, double interestRate) {
        super(accountNumber, name, age, balance, "SAVINGS", status, pin);
        this.minBalance = minBalance;
        this.interestRate = interestRate;
    }

    @Override
    public void processDebit(double amount) throws AccountException {
        if (balance - amount < minBalance) {
            throw new MinimumBalanceViolationException(
                    "Cannot breach minimum balance of Rs " + minBalance);
        }
        balance -= amount;
    }

    public void applyInterest() {
        balance += balance * (interestRate / 100.0);
    }

    public double getMinBalance() { return minBalance; }
    public double getInterestRate() { return interestRate; }
}
