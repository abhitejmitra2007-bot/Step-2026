package com.gdb.tests;

import com.gdb.domain.*;

public class TestAccountRulesEngineProperties {
    public void newTenureTest() {
        if (AccountRulesEngine.getSavingsMinBalance(0) == 10000.0 && AccountRulesEngine.getSavingsInterestRate(0) == 2.70) {
            System.out.println("Test Success");
        } else {
            System.out.println("Test Fail");
        }
    }

    public void standardTenureTest() {
        if (AccountRulesEngine.getSavingsMinBalance(2) == 7500.0 && AccountRulesEngine.getSavingsInterestRate(2) == 3.00) {
            System.out.println("Test Success");
        } else {
            System.out.println("Test Fail");
        }
    }

    public void premiumTenureTest() {
        if (AccountRulesEngine.getSavingsMinBalance(4) == 5000.0 && AccountRulesEngine.getSavingsInterestRate(4) == 3.50) {
            System.out.println("Test Success");
        } else {
            System.out.println("Test Fail");
        }
    }

    public void privilegeTenureTest() {
        if (AccountRulesEngine.getSavingsMinBalance(6) == 2500.0 && AccountRulesEngine.getSavingsInterestRate(6) == 4.00) {
            System.out.println("Test Success");
        } else {
            System.out.println("Test Fail");
        }
    }

    public void propertiesLoaderTest() {
        AccountRulesPropertiesLoader loader = new AccountRulesPropertiesLoader("src/main/resources/config/rules/savings.properties");
        if (loader.getDouble("min.balance.new", 0) == 10000.0
                && loader.getDouble("interest.rate.privilege", 0) == 4.00
                && loader.getProperty("missing", "default").equals("default")) {
            System.out.println("Test Success");
        } else {
            System.out.println("Test Fail");
        }
    }

    public void currentRuleTest() {
        if (AccountRulesEngine.getCurrentOverdraftLimit(5000) == 25000.0
                && AccountRulesEngine.getCurrentOverdraftLimit(20000) == 50000.0) {
            System.out.println("Test Success");
        } else {
            System.out.println("Test Fail");
        }
    }

    public void fixedDepositRuleTest() {
        if (AccountRulesEngine.getFDInterestRate(6) == 5.00
                && AccountRulesEngine.getFDInterestRate(12) == 6.50
                && AccountRulesEngine.getFDInterestRate(36) == 7.50) {
            System.out.println("Test Success");
        } else {
            System.out.println("Test Fail");
        }
    }

    public static void main(String[] args) {
        TestAccountRulesEngineProperties test = new TestAccountRulesEngineProperties();
        test.newTenureTest();
        test.standardTenureTest();
        test.premiumTenureTest();
        test.privilegeTenureTest();
        test.propertiesLoaderTest();
        test.currentRuleTest();
        test.fixedDepositRuleTest();
    }
}
