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
    void testCreateAccount() throws InvalidMobileNumberException, InvalidinitialbalException, InvalidAccountException {
        bank = new Bank();
        String accno = bank.createAccount("Raj", 500.0, "0987654321");
        BankAccount account = bank.getAccount(accno);
        assertNotNull(account);
        assertEquals(accno, account.getAccountNumber());
    }

    @Test
    void testDeleteAccount() throws InvalidAccountException, InvalidMobileNumberException, InvalidinitialbalException {

        bank = new Bank();
        String accnumber;
        accnumber = bank.createAccount("Tanya", 1000.0, "1234567890");

        bank.deleteAccount(accnumber);
        try {
            BankAccount account = bank.getAccount(accnumber);
            assertNull(account);
        } catch (InvalidAccountException e) {
            // If exception is thrown, account is indeed deleted, so the test should pass
            assertNull(null);
        }

    }

}