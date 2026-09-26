package com.gdb.tests;

import com.gdb.domain.*;

public class TestInterfaceFactory {
    public void savingsFactoryTest() {
        IAccount account = AccountFactory.createAccount("SAVINGS", "S1001", "Rajesh Sharma", 30, 10000, "ACTIVE", "1234");
        if (account != null && account.getAccountType().equals("SAVINGS") && account.getName().equals("Rajesh Sharma")) {
            System.out.println("Test Success");
        } else {
            System.out.println("Test Fail");
        }
    }

    public void currentFactoryTest() {
        IAccount account = AccountFactory.createAccount("CURRENT", "C1001", "Priya Patel", 28, 20000, "ACTIVE", "1234");
        if (account != null && account.getAccountType().equals("CURRENT") && account.getBalance() == 20000) {
            System.out.println("Test Success");
        } else {
            System.out.println("Test Fail");
        }
    }

    public void fixedDepositFactoryTest() {
        IAccount account = AccountFactory.createAccount("FIXED_DEPOSIT", "F1001", "Amit Kumar", 35, 30000, "ACTIVE", "1234");
        if (account != null && account.getAccountType().equals("FIXED_DEPOSIT") && account.getBalance() == 30000) {
            System.out.println("Test Success");
        } else {
            System.out.println("Test Fail");
        }
    }

    public void salaryFactoryTest() {
        IAccount account = AccountFactory.createAccount("SALARY", "S1002", "Sneha Verma", 27, 25000, "ACTIVE", "1234");
        if (account != null && account.getAccountType().equals("SALARY") && account.getBalance() == 25000) {
            System.out.println("Test Success");
        } else {
            System.out.println("Test Fail");
        }
    }

    public void invalidTypeTest() {
        boolean failed = false;
        try {
            AccountFactory.createAccount("UNKNOWN", "X1001", "Test User", 25, 5000, "ACTIVE", "1234");
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
        test.savingsFactoryTest();
        test.currentFactoryTest();
        test.fixedDepositFactoryTest();
        test.salaryFactoryTest();
        test.invalidTypeTest();
    }
}
