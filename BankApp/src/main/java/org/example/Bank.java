package org.example;

import java.util.*;
class Bank {
    private Map<String, BankAccount> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public String createAccount(String accountHolderName, double initialBalance, String mobileNumber) throws InvalidMobileNumberException, InvalidinitialbalException {
        BankAccount newAccount = new BankAccount(accountHolderName, initialBalance, mobileNumber);
        accounts.put(newAccount.getAccountNumber(), newAccount);
        System.out.println("Account created successfully with Account Number: " + newAccount.getAccountNumber() + "  With Name : " + newAccount.getAccountHolderName());
        return newAccount.getAccountNumber();
    }

    public void deleteAccount(String accountNumber) throws InvalidAccountException {
        if (accounts.containsKey(accountNumber)) {
            accounts.remove(accountNumber);
            System.out.println("Account deleted successfully");
        } else {
            throw new InvalidAccountException(accountNumber);
        }
    }


    public void updateAccountDetails(String accountNumber) throws InvalidAccountException, InvalidMobileNumberException {
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
                    if (newMobileNumber.matches("[0-9]{10}")) { // Assuming a valid mobile number has 10 digits
                        account.updateMobileNumber(newMobileNumber);
                    } else {
                        throw new InvalidMobileNumberException(newMobileNumber);
                    }
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } else {
            throw new InvalidAccountException(accountNumber);
        }
    }


    public BankAccount getAccount(String accountNumber) throws InvalidAccountException{
        if(accounts.containsKey(accountNumber))
        {
            return accounts.get(accountNumber);
        }
        else{

            throw new InvalidAccountException(accountNumber);

        }
    }

    public void printAccountDetails(String accountNumber) throws InvalidAccountException {
        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Account Holder: " + account.getAccountHolderName());
            System.out.println("Balance: " + account.getBalance());
            System.out.println("Mobile Number: " + account.getMobileNumber());
            System.out.println("Transaction History: " + account.getTransactionHistory());
        } else {
            throw new InvalidAccountException(accountNumber);
        }
    }
}

class InvalidAccountException extends Exception {
    public InvalidAccountException(String accountNumber) {
        super("Invalid account number: " + accountNumber);
    }
}

class InvalidMobileNumberException extends Exception {
    public InvalidMobileNumberException(String mobileNumber) {
        super("Invalid mobile number: " + mobileNumber + "Enter a 10 digit mobile number");
    }
}
