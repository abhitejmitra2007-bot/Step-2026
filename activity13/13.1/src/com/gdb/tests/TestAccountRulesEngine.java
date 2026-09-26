package com.gdb.tests;

import com.gdb.domain.AccountRulesEngine;

public class TestAccountRulesEngine {
    public void newCustomerRulesTest() {
        if (AccountRulesEngine.getSavingsMinBalance(0) == 10000.0 && AccountRulesEngine.getSavingsInterestRate(0) == 2.70) {
            System.out.println("Test Success");
        } else {
            System.out.println("Test Fail");
        }
    }

    public void standardCustomerRulesTest() {
        if (AccountRulesEngine.getSavingsMinBalance(2) == 7500.0 && AccountRulesEngine.getSavingsInterestRate(2) == 3.00) {
            System.out.println("Test Success");
        } else {
            System.out.println("Test Fail");
        }
    }

    public void premiumCustomerRulesTest() {
        if (AccountRulesEngine.getSavingsMinBalance(4) == 5000.0 && AccountRulesEngine.getSavingsInterestRate(4) == 3.50) {
            System.out.println("Test Success");
        } else {
            System.out.println("Test Fail");
        }
    }

    public void privilegeCustomerRulesTest() {
        if (AccountRulesEngine.getSavingsMinBalance(6) == 2500.0 && AccountRulesEngine.getSavingsInterestRate(6) == 4.00) {
            System.out.println("Test Success");
        } else {
            System.out.println("Test Fail");
        }
    }

    public void currentOverdraftRuleTest() {
        if (AccountRulesEngine.getCurrentOverdraftLimit(5000) == 25000.0 && AccountRulesEngine.getCurrentOverdraftLimit(20000) == 50000.0) {
            System.out.println("Test Success");
        } else {
            System.out.println("Test Fail");
        }
    }

    public void fixedDepositInterestRuleTest() {
        if (AccountRulesEngine.getFDInterestRate(6) == 5.00
                && AccountRulesEngine.getFDInterestRate(12) == 6.50
                && AccountRulesEngine.getFDInterestRate(36) == 7.50) {
            System.out.println("Test Success");
        } else {
            System.out.println("Test Fail");
        }
    }

    public static void main(String[] args) {
        TestAccountRulesEngine test = new TestAccountRulesEngine();
        test.newCustomerRulesTest();
        test.standardCustomerRulesTest();
        test.premiumCustomerRulesTest();
        test.privilegeCustomerRulesTest();
        test.currentOverdraftRuleTest();
        test.fixedDepositInterestRuleTest();
    }
}
