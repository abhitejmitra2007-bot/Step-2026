package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.exceptions.*;

public class TestAbstractAccount {
    public static void main(String[] args) {
        System.out.println("=== Activity 9: Abstract Account & Template Pattern ===");

        AbstractAccount savings = new SavingsAccount("SAV1001", "Rajesh Sharma", 28,
                10000.0, "ACTIVE", "1234", 1000.0, 4.0);
        try {
            savings.withdraw(2000.0, "1234");
            if (savings.getBalance() == 8000.0) {
                System.out.println("[Savings] Withdraw 2000: SUCCESS | Balance: Rs " + savings.getBalance());
            } else {
                System.out.println("[Savings] [FAIL]");
            }
        } catch (AccountException e) {
            System.out.println("[Savings] [FAIL]");
        }

        try {
            savings.withdraw(8000.0, "1234");
            System.out.println("[Savings] [FAIL]");
        } catch (MinimumBalanceViolationException e) {
            System.out.println("[Savings] Withdraw below min balance: Caught MinimumBalanceViolationException [PASS]");
        } catch (AccountException e) {
            System.out.println("[Savings] [FAIL]");
        }

        AbstractAccount current = new CurrentAccount("CUR1001", "Priya Patel", 34,
                2000.0, "ACTIVE", "5678", 10000.0);
        try {
            current.withdraw(5000.0, "5678");
            if (current.getBalance() == -3000.0) {
                System.out.println("[Current] Overdraft debit: SUCCESS | Balance: Rs " + current.getBalance());
            } else {
                System.out.println("[Current] [FAIL]");
            }
        } catch (AccountException e) {
            System.out.println("[Current] [FAIL]");
        }

        AbstractAccount fixedDeposit = new FixedDepositAccount("FD1001", "Amit Kumar", 45,
                50000.0, "ACTIVE", "1111", 12, 6.5);
        try {
            fixedDeposit.withdraw(5000.0, "1111");
            System.out.println("[FixedDeposit] [FAIL]");
        } catch (AccountException e) {
            System.out.println("[FixedDeposit] Premature debit: Caught AccountException [PASS]");
        }

        System.out.println("Template method pattern executed successfully!");
    }
}
