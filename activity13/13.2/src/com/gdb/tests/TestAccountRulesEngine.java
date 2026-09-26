package com.gdb.tests;

import com.gdb.domain.*;

public class TestAccountRulesEngine {
    public void zeroYearRuleTest() {
        SavingsAccount account = (SavingsAccount) AccountFactory.createAccount("SAVINGS", "S1001", "Rajesh Sharma", 28, 50000.0, "ACTIVE", "1234", 0);
        if (account.getTenureYears() == 0 && account.getMinBalance() == 10000.0 && account.getInterestRate() == 2.70) {
            System.out.println("Test Success");
        } else {
            System.out.println("Test Fail");
        }
    }

    public void twoYearRuleTest() {
        SavingsAccount account = (SavingsAccount) AccountFactory.createAccount("SAVINGS", "S1002", "Rajesh Sharma", 28, 50000.0, "ACTIVE", "1234", 2);
        if (account.getTenureYears() == 2 && account.getMinBalance() == 7500.0 && account.getInterestRate() == 3.00) {
            System.out.println("Test Success");
        } else {
            System.out.println("Test Fail");
        }
    }

    public void fourYearRuleTest() {
        SavingsAccount account = (SavingsAccount) AccountFactory.createAccount("SAVINGS", "S1003", "Rajesh Sharma", 28, 50000.0, "ACTIVE", "1234", 4);
        if (account.getTenureYears() == 4 && account.getMinBalance() == 5000.0 && account.getInterestRate() == 3.50) {
            System.out.println("Test Success");
        } else {
            System.out.println("Test Fail");
        }
    }

    public void sixYearRuleTest() {
        SavingsAccount account = (SavingsAccount) AccountFactory.createAccount("SAVINGS", "S1004", "Rajesh Sharma", 28, 50000.0, "ACTIVE", "1234", 6);
        if (account.getTenureYears() == 6 && account.getMinBalance() == 2500.0 && account.getInterestRate() == 4.00) {
            System.out.println("Test Success");
        } else {
            System.out.println("Test Fail");
        }
    }

    public static void main(String[] args) {
        TestAccountRulesEngine test = new TestAccountRulesEngine();
        test.zeroYearRuleTest();
        test.twoYearRuleTest();
        test.fourYearRuleTest();
        test.sixYearRuleTest();
    }
}
