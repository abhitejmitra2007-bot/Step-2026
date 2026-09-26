package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.service.TransferService;

import java.time.LocalDateTime;

public class TestTransactionModel {
    public void transactionModelTest() {
        LocalDateTime time = LocalDateTime.now();
        Transaction transaction = new Transaction("TXN-1", time, 1001, TransactionType.DEPOSIT, 5000, 55000, "SUCCESS", "Deposit", 0, 0);
        transaction.setStatus("SUCCESS");
        transaction.setToAccount(1002);
        if (transaction.getTransactionId().equals("TXN-1")
                && transaction.getTimestamp().equals(time)
                && transaction.getAccountNumber() == 1001
                && transaction.getType() == TransactionType.DEPOSIT
                && transaction.getAmount() == 5000
                && transaction.getBalanceAfter() == 55000
                && transaction.getStatus().equals("SUCCESS")
                && transaction.getToAccount() == 1002
                && transaction.toString().contains("DEPOSIT")) {
            System.out.println("Test Success");
        } else {
            System.out.println("Test Fail");
        }
    }

    public void depositTransactionTest() {
        try {
            Account account = (Account) AccountFactory.createAccount("SAVINGS", 1001, "Rajesh Sharma", 30, 50000);
            account.setPin(1234);
            Transaction transaction = account.depositWithTransaction(5000);
            System.out.println(transaction);
            if (transaction.getType() == TransactionType.DEPOSIT
                    && transaction.getAmount() == 5000
                    && transaction.getBalanceAfter() == 55000
                    && transaction.getStatus().equals("SUCCESS")) {
                System.out.println("Test Success");
            } else {
                System.out.println("Test Fail");
            }
        } catch (Exception e) {
            System.out.println("Test Fail");
        }
    }

    public void withdrawalTransactionTest() {
        try {
            Account account = (Account) AccountFactory.createAccount("SAVINGS", 1001, "Rajesh Sharma", 30, 50000);
            account.setPin(1234);
            account.depositWithTransaction(5000);
            Transaction transaction = account.withdrawWithTransaction(2000, 1234);
            System.out.println(transaction);
            if (transaction.getType() == TransactionType.WITHDRAW
                    && transaction.getAmount() == 2000
                    && transaction.getBalanceAfter() == 53000
                    && transaction.getStatus().equals("SUCCESS")) {
                System.out.println("Test Success");
            } else {
                System.out.println("Test Fail");
            }
        } catch (Exception e) {
            System.out.println("Test Fail");
        }
    }

    public void transferTransactionTest() {
        try {
            Account acc1 = (Account) AccountFactory.createAccount("SAVINGS", 1001, "Rajesh Sharma", 30, 50000);
            Account acc2 = (Account) AccountFactory.createAccount("SAVINGS", 1002, "Priya Patel", 28, 20000);
            acc1.setPin(1234);
            acc1.depositWithTransaction(5000);
            acc1.withdrawWithTransaction(2000, 1234);
            Transaction transaction = new TransferService().transferWithTransaction(acc1, acc2, 1000, 1234);
            System.out.println(transaction);
            if (transaction.getType() == TransactionType.TRANSFER
                    && transaction.getAmount() == 1000
                    && transaction.getBalanceAfter() == 52000
                    && transaction.getFromAccount() == 1001
                    && transaction.getToAccount() == 1002
                    && acc2.getBalance() == 21000
                    && transaction.getStatus().equals("SUCCESS")) {
                System.out.println("Test Success");
            } else {
                System.out.println("Test Fail");
            }
        } catch (Exception e) {
            System.out.println("Test Fail");
        }
    }

    public void legacyDepositTest() {
        try {
            Account account = (Account) AccountFactory.createAccount("SAVINGS", 1001, "Rajesh Sharma", 30, 50000);
            account.setPin(1234);
            account.depositWithTransaction(5000);
            account.withdrawWithTransaction(2000, 1234);
            new TransferService().transferWithTransaction(
                    account,
                    (Account) AccountFactory.createAccount("SAVINGS", 1002, "Priya Patel", 28, 20000),
                    1000,
                    1234
            );
            account.deposit(1000);
            if (account.getBalance() == 53000 && account.getAccountInfo().contains("Rs. 53000.0")) {
                System.out.println("Test Success");
            } else {
                System.out.println("Test Fail");
            }
        } catch (Exception e) {
            System.out.println("Test Fail");
        }
    }

    public static void main(String[] args) {
        TestTransactionModel test = new TestTransactionModel();
        test.transactionModelTest();
        test.depositTransactionTest();
        test.withdrawalTransactionTest();
        test.transferTransactionTest();
        test.legacyDepositTest();
    }
}
