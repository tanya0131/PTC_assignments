package org.example;
import java.util.*;
class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private String mobileNumber;
    private List<String> transactionHistory;

    public BankAccount(String accountHolderName, double initialBalance, String mobileNumber) {
        this.accountNumber = generateAccountNumber();
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.mobileNumber = mobileNumber;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with balance " + initialBalance);
    }

    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumberBuilder = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            accountNumberBuilder.append(random.nextInt(10));
        }
        return accountNumberBuilder.toString();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount + ", New Balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: " + amount + ", New Balance: " + balance);
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    public void transfer(BankAccount toAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            toAccount.deposit(amount);
            transactionHistory.add("Transferred: " + amount + " to Account: " + toAccount.getAccountNumber() + ", New Balance: " + balance);
        } else {
            System.out.println("Invalid transfer amount.");
        }
    }

    public void updateAccountHolderName(String newName) {
        accountHolderName = newName;
        transactionHistory.add("Account holder name changed to: " + newName);
    }

    public void updateMobileNumber(String newNumber) {
        mobileNumber = newNumber;
        transactionHistory.add("Account holder mobile number changed to: " + newNumber);
    }
}
