package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    Bank bank;


//    @BeforeEach
//    void setUp() {
//
//
//    }

    @Test
    void testCreateAccount() {
        bank = new Bank();
        String accno = bank.createAccount("Raj", 500.0, "0987654321");
        BankAccount account = bank.getAccount(accno);
        assertNotNull(account);
        assertEquals(accno, account.getAccountNumber());
    }

    @Test
    void testDeleteAccount() {

        bank = new Bank();
        String accnumber;
        accnumber = bank.createAccount("Tanya", 1000.0, "1234567890");
        bank.deleteAccount(accnumber);
        assertNull(bank.getAccount(accnumber));
    }

}