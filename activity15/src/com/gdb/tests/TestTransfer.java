package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.service.TransferService;
import com.gdb.exceptions.*;

public class TestTransfer {
    private Account createSavings(int number, String name, double balance) throws AccountException {
        return (Account) AccountFactory.createAccount("SAVINGS", number, name, 30, balance, 0);
    }

    public void accountCreationTest() {
        try {
            Account acc1 = createSavings(1001, "Rajesh Sharma", 100000);
            Account acc2 = createSavings(1002, "Priya Patel", 20000);
            acc1.setPin(1234);
            if (acc1.getBalance() == 100000 && acc2.getBalance() == 20000 && acc1.getDailyTransferLimit() == 50000) {
                System.out.println("Test Success");
            } else {
                System.out.println("Test Fail");
            }
        } catch (Exception e) {
            System.out.println("Test Fail");
        }
    }

    public void successfulTransferTest() {
        try {
            Account acc1 = createSavings(1001, "Rajesh Sharma", 100000);
            Account acc2 = createSavings(1002, "Priya Patel", 20000);
            acc1.setPin(1234);
            new TransferService().transfer(acc1, acc2, 5000, 1234);
            if (acc1.getBalance() == 95000 && acc2.getBalance() == 25000 && acc1.getDailyTransferTotal() == 5000) {
                System.out.println("Test Success");
            } else {
                System.out.println("Test Fail");
            }
        } catch (Exception e) {
            System.out.println("Test Fail");
        }
    }

    public void insufficientBalanceTest() {
        try {
            Account acc1 = createSavings(1001, "Rajesh Sharma", 100000);
            Account acc2 = createSavings(1002, "Priya Patel", 20000);
            acc1.setPin(1234);
            boolean failed = false;
            try {
                new TransferService().transfer(acc1, acc2, 100000, 1234);
            } catch (InsufficientBalanceException e) {
                failed = true;
            }
            if (failed && acc1.getBalance() == 100000 && acc2.getBalance() == 20000) {
                System.out.println("Test Success");
            } else {
                System.out.println("Test Fail");
            }
        } catch (Exception e) {
            System.out.println("Test Fail");
        }
    }

    public void dailyLimitTest() {
        try {
            Account acc1 = createSavings(1001, "Rajesh Sharma", 100000);
            Account acc2 = createSavings(1002, "Priya Patel", 20000);
            acc1.setPin(1234);
            TransferService service = new TransferService();
            service.transfer(acc1, acc2, 5000, 1234);
            service.transfer(acc1, acc2, 20000, 1234);
            service.transfer(acc1, acc2, 20000, 1234);
            boolean failed = false;
            try {
                service.transfer(acc1, acc2, 20000, 1234);
            } catch (AccountException e) {
                failed = true;
            }
            if (failed && acc1.getDailyTransferTotal() == 45000 && acc1.getBalance() == 55000 && acc2.getBalance() == 65000) {
                System.out.println("Test Success");
            } else {
                System.out.println("Test Fail");
            }
        } catch (Exception e) {
            System.out.println("Test Fail");
        }
    }

    public void remainingLimitTest() {
        try {
            Account acc1 = createSavings(1001, "Rajesh Sharma", 100000);
            acc1.setPin(1234);
            Account acc2 = createSavings(1002, "Priya Patel", 20000);
            TransferService service = new TransferService();
            service.transfer(acc1, acc2, 5000, 1234);
            service.transfer(acc1, acc2, 20000, 1234);
            service.transfer(acc1, acc2, 20000, 1234);
            if (acc1.getDailyTransferTotal() + acc1.getRemainingDailyTransferLimit() == acc1.getDailyTransferLimit()
                    && acc1.getRemainingDailyTransferLimit() == 5000) {
                System.out.println("Test Success");
            } else {
                System.out.println("Test Fail");
            }
        } catch (Exception e) {
            System.out.println("Test Fail");
        }
    }

    public void invalidPinTest() {
        try {
            Account acc1 = createSavings(1001, "Rajesh Sharma", 100000);
            Account acc2 = createSavings(1002, "Priya Patel", 20000);
            acc1.setPin(1234);
            boolean failed = false;
            try {
                new TransferService().transfer(acc1, acc2, 5000, 9999);
            } catch (InvalidPinException e) {
                failed = true;
            }
            if (failed && acc1.getBalance() == 100000 && acc2.getBalance() == 20000) {
                System.out.println("Test Success");
            } else {
                System.out.println("Test Fail");
            }
        } catch (Exception e) {
            System.out.println("Test Fail");
        }
    }

    public void inactiveAccountTest() {
        try {
            Account acc1 = createSavings(1001, "Rajesh Sharma", 100000);
            Account acc2 = createSavings(1002, "Priya Patel", 20000);
            acc1.setPin(1234);
            acc1.closeAccount();
            boolean failed = false;
            try {
                new TransferService().transfer(acc1, acc2, 5000, 1234);
            } catch (InactiveAccountException e) {
                failed = true;
            }
            if (failed && acc1.getBalance() == 100000 && acc2.getBalance() == 20000) {
                System.out.println("Test Success");
            } else {
                System.out.println("Test Fail");
            }
        } catch (Exception e) {
            System.out.println("Test Fail");
        }
    }

    public void fixedDepositTransferTest() {
        try {
            Account fd = (Account) AccountFactory.createAccount("FIXEDDEPOSIT", 1003, "Amit Kumar", 35, 30000, 0);
            if (!fd.canTransfer(1) && fd.getDailyTransferLimit() == 0) {
                System.out.println("Test Success");
            } else {
                System.out.println("Test Fail");
            }
        } catch (Exception e) {
            System.out.println("Test Fail");
        }
    }

    public static void main(String[] args) {
        TestTransfer test = new TestTransfer();
        test.accountCreationTest();
        test.successfulTransferTest();
        test.insufficientBalanceTest();
        test.dailyLimitTest();
        test.remainingLimitTest();
        test.invalidPinTest();
        test.inactiveAccountTest();
        test.fixedDepositTransferTest();
    }
}
