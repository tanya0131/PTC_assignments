package org.example;

import java.util.*;
class Bank {
    private Map<String, BankAccount> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public String createAccount(String accountHolderName, double initialBalance, String mobileNumber) {
        BankAccount newAccount = new BankAccount(accountHolderName, initialBalance, mobileNumber);
        accounts.put(newAccount.getAccountNumber(), newAccount);
        System.out.println("Account created successfully with Account Number: " + newAccount.getAccountNumber() + "  With Name : " + newAccount.getAccountHolderName());
        return newAccount.getAccountNumber();
    }

    public void deleteAccount(String accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            accounts.remove(accountNumber);
            System.out.println("Account deleted successfully");
        } else {
            System.out.println("Account not found");
        }
    }

    public void updateAccountDetails(String accountNumber) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which field do you want to update?");
        System.out.println("1.) Account Holder Name 2.) Linked Mobile Number");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (accounts.containsKey(accountNumber)) {
            BankAccount account = accounts.get(accountNumber);
            switch (choice) {
                case 1:
                    System.out.println("Enter the new Account Holder Name:");
                    String newAccountHolderName = scanner.nextLine();
                    account.updateAccountHolderName(newAccountHolderName);
                    break;
                case 2:
                    System.out.println("Enter the new Mobile Number:");
                    String newMobileNumber = scanner.nextLine();
                    account.updateMobileNumber(newMobileNumber);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } else {
            System.out.println("Account not found");
        }
    }

    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void printAccountDetails(String accountNumber) {
        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Account Holder: " + account.getAccountHolderName());
            System.out.println("Balance: " + account.getBalance());
            System.out.println("Mobile Number: " + account.getMobileNumber());
            System.out.println("Transaction History: " + account.getTransactionHistory());
        } else {
            System.out.println("Account not found.");
        }
    }
}
