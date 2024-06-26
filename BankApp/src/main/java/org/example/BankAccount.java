package org.example;
import java.util.*;
class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private String mobileNumber;
    private List<String> transactionHistory;

    public BankAccount(String accountHolderName, double initialBalance, String mobileNumber) throws InvalidMobileNumberException,InvalidinitialbalException {
        if(mobileNumber.matches("[0-9]{10}") && initialBalance>0)
        {
            this.accountNumber = generateAccountNumber();
            this.accountHolderName = accountHolderName;
            this.balance = initialBalance;
            this.mobileNumber = mobileNumber;
            this.transactionHistory = new ArrayList<>();
            transactionHistory.add("Account created with balance " + initialBalance);
        }
        else if (!mobileNumber.matches("[0-9]{10}")) {
            throw new InvalidMobileNumberException(mobileNumber);
        }
        else if(initialBalance<=0){
            throw new InvalidinitialbalException(initialBalance);
        }

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

    public void deposit(double amount) throws invalidamountexception {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount + ", New Balance: " + balance);
        } else {
            throw new invalidamountexception(amount);
        }
    }

    public void withdraw(double amount) throws invalidamountexception,lessbalanceexception {
        if(amount<0)
        {
            throw new invalidamountexception(amount);
        }
        else if(amount>balance){

            throw new lessbalanceexception(amount,balance);
        }
        else{
            balance -= amount;
            transactionHistory.add("Withdrew: " + amount + ", New Balance: " + balance);
        }

    }

    public void transfer(BankAccount toAccount, double amount) throws invalidamountexception, lessbalanceexception {
        if(amount<0)
        {
            throw new invalidamountexception(amount);
        }
        else if(amount>balance){

            throw new lessbalanceexception(amount,balance);
        }
        else{
            balance -= amount;
            toAccount.deposit(amount);
            transactionHistory.add("Transferred: " + amount + " to Account: " + toAccount.getAccountNumber() + ", New Balance: " + balance);
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


class invalidamountexception extends Exception{

    String msg=" ";
    double bal;
    public invalidamountexception(double amount)
    {
        msg = "You have entered a negative amount.";
        bal = amount;
    }

}

class lessbalanceexception extends Exception{

    double withdrawamount;
    double b;
    public lessbalanceexception(double amount,double balance)
    {
        withdrawamount = amount;
        b = balance;
    }
}

class InvalidinitialbalException extends Exception
{
    public  InvalidinitialbalException(double initialBalance) {
        super("Minimum balance to open a account should be greater than 0");
    }
}
