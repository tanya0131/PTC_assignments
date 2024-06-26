package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class BankAccountTest {

    BankAccount account;
    @BeforeEach
    void setUp() throws InvalidMobileNumberException, InvalidinitialbalException {
        account = new BankAccount("Tanya", 1000.0, "1234567890");
    }

    @Test
    void testDeposit() throws invalidamountexception {
        account.deposit(500.0);
        assertEquals(1500.0, account.getBalance());
    }

    @Test
    void testWithdraw() throws invalidamountexception, lessbalanceexception {
        account.withdraw(500.0);
        assertEquals(500.0, account.getBalance());
    }


    @Test
    void testTransfer() throws invalidamountexception, lessbalanceexception, InvalidMobileNumberException, InvalidinitialbalException {
        BankAccount toAccount = new BankAccount("Saurabh", 500.0, "0987654321");
        account.transfer(toAccount, 300.0);
        assertEquals(700.0, account.getBalance());
        assertEquals(800.0, toAccount.getBalance());
    }

    @Test
    void testUpdateAccountHolderName() {
        account.updateAccountHolderName("swati");
        assertEquals("swati", account.getAccountHolderName());
    }

    @Test
    void testUpdateMobileNumber() {
        account.updateMobileNumber("7890123456");
        assertEquals("7890123456", account.getMobileNumber());
    }

    @Test
    void testTransactionHistory() throws invalidamountexception, lessbalanceexception {
        account.deposit(500.0);
        account.withdraw(200.0);
        List<String> history = account.getTransactionHistory();
        assertTrue(history.contains("Deposited: 500.0, New Balance: 1500.0"));
        assertTrue(history.contains("Withdrew: 200.0, New Balance: 1300.0"));
    }
}

