package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.exceptions.*;

public class TestInterfaceFactory {
    public void savingsAccountTest() {
        try {
            IAccount account = AccountFactory.createAccount("SAVINGS", "S1001", "Rahul Sharma", 25, 5000, "ACTIVE", "1234");
            if (account != null && account.getAccountType().equals("SAVINGS") && account.getBalance() == 5000) {
                System.out.println("Test Success");
            } else {
                System.out.println("Test Fail");
            }
        } catch (Exception e) {
            System.out.println("Test Fail");
        }
    }

    public void savingsDepositTest() {
        try {
            IAccount account = AccountFactory.createAccount("SAVINGS", "S1002", "Rahul Sharma", 25, 5000, "ACTIVE", "1234");
            account.deposit(1000);
            if (account.getBalance() == 6000) {
                System.out.println("Test Success");
            } else {
                System.out.println("Test Fail");
            }
        } catch (Exception e) {
            System.out.println("Test Fail");
        }
    }

    public void savingsMinimumBalanceTest() {
        try {
            IAccount account = AccountFactory.createAccount("SAVINGS", "S1003", "Rahul Sharma", 25, 2000, "ACTIVE", "1234");
            boolean failed = false;
            try {
                account.withdraw(1001, "1234");
            } catch (MinimumBalanceViolationException e) {
                failed = true;
            }
            if (failed && account.getBalance() == 2000) {
                System.out.println("Test Success");
            } else {
                System.out.println("Test Fail");
            }
        } catch (Exception e) {
            System.out.println("Test Fail");
        }
    }

    public void currentAccountTest() {
        try {
            IAccount account = AccountFactory.createAccount("CURRENT", "C1001", "Aman Verma", 30, 10000, "ACTIVE", "5678");
            account.withdraw(30000, "5678");
            if (account.getBalance() == -20000) {
                System.out.println("Test Success");
            } else {
                System.out.println("Test Fail");
            }
        } catch (Exception e) {
            System.out.println("Test Fail");
        }
    }

    public void currentOverdraftLimitTest() {
        try {
            IAccount account = AccountFactory.createAccount("CURRENT", "C1002", "Aman Verma", 30, 10000, "ACTIVE", "5678");
            boolean failed = false;
            try {
                account.withdraw(35001, "5678");
            } catch (InsufficientBalanceException e) {
                failed = true;
            }
            if (failed && account.getBalance() == 10000) {
                System.out.println("Test Success");
            } else {
                System.out.println("Test Fail");
            }
        } catch (Exception e) {
            System.out.println("Test Fail");
        }
    }

    public void fixedDepositTest() {
        try {
            IAccount account = AccountFactory.createAccount("FIXED_DEPOSIT", "F1001", "Neha Gupta", 28, 20000, "ACTIVE", "9999");
            boolean failed = false;
            try {
                account.withdraw(1000, "9999");
            } catch (AccountException e) {
                failed = true;
            }
            if (failed && account.getBalance() == 20000) {
                System.out.println("Test Success");
            } else {
                System.out.println("Test Fail");
            }
        } catch (Exception e) {
            System.out.println("Test Fail");
        }
    }

    public void invalidAccountTypeTest() {
        boolean failed = false;
        try {
            AccountFactory.createAccount("UNKNOWN", "X1001", "Test User", 25, 5000, "ACTIVE", "1111");
        } catch (IllegalArgumentException e) {
            failed = true;
        }
        if (failed) {
            System.out.println("Test Success");
        } else {
            System.out.println("Test Fail");
        }
    }

    public static void main(String[] args) {
        TestInterfaceFactory test = new TestInterfaceFactory();
        test.savingsAccountTest();
        test.savingsDepositTest();
        test.savingsMinimumBalanceTest();
        test.currentAccountTest();
        test.currentOverdraftLimitTest();
        test.fixedDepositTest();
        test.invalidAccountTypeTest();
    }
}
