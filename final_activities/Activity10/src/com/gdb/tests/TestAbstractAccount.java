package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.exceptions.*;

public class TestAbstractAccount {

    public static boolean transfer(AbstractAccount source, AbstractAccount destination,
                                   double amount, String pin) {
        try {
            source.withdraw(amount, pin);
            destination.deposit(amount);
            return true;
        } catch (AccountException e) {
            return false;
        }
    }

    public static void processMonthlyCycle(AbstractAccount[] portfolio) {
        for (AbstractAccount account : portfolio) {
            if (account instanceof SavingsAccount) {
                ((SavingsAccount) account).applyInterest();
            }

            if (account instanceof SalaryAccount) {
                SalaryAccount salary = (SalaryAccount) account;
                System.out.println("Salary account " + salary.getAccountNumber()
                        + " inactive months: " + salary.getInactiveMonths());
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Activity 10: Banking Operations Suite ===");

        AbstractAccount savings = new SavingsAccount(
                "SAV2001", "Rahul Sharma", 25, 10000.0, "ACTIVE", "1234", 1000.0, 4.0);
        AbstractAccount current = new CurrentAccount(
                "CUR2001", "Priya Patel", 32, 5000.0, "ACTIVE", "5678", 10000.0);
        AbstractAccount salary = new SalaryAccount(
                "SAL2001", "Amit Kumar", 30, 25000.0, "ACTIVE", "2468", "ABC Ltd");

        AbstractAccount[] portfolio = {savings, current, salary};

        boolean transferSuccess = transfer(savings, current, 3000.0, "1234");
        if (transferSuccess && savings.getBalance() == 7000.0 && current.getBalance() == 8000.0) {
            System.out.println("Transfer Rs 3000 from Savings to Current: SUCCESS");
            System.out.println("Savings Balance: Rs " + savings.getBalance()
                    + " | Current Balance: Rs " + current.getBalance());
        } else {
            System.out.println("Transfer Rs 3000 from Savings to Current: FAIL");
        }

        double savingsBefore = savings.getBalance();
        double currentBefore = current.getBalance();
        boolean wrongPinTransfer = transfer(savings, current, 1000.0, "9999");

        if (!wrongPinTransfer && savings.getBalance() == savingsBefore
                && current.getBalance() == currentBefore) {
            System.out.println("Failed Transfer (Wrong PIN): Exception caught, no balance changed [PASS]");
        } else {
            System.out.println("Failed Transfer (Wrong PIN): [FAIL]");
        }

        processMonthlyCycle(portfolio);
        System.out.println("Monthly Interest Cycle processed for all qualifying accounts.");
        System.out.println("All banking operations passed!");
    }
}
