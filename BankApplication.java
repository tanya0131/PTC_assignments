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

class Bank {
    private Map<String, BankAccount> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public void createAccount(String accountHolderName, double initialBalance, String mobileNumber) {
        BankAccount newAccount = new BankAccount(accountHolderName, initialBalance, mobileNumber);
        accounts.put(newAccount.getAccountNumber(), newAccount);
        System.out.println("Account created successfully with Account Number: " + newAccount.getAccountNumber());
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

public class BankApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        try {
            System.out.print("Enter account holder name: ");
            String accountHolderName1 = scanner.nextLine();

            System.out.print("Enter initial balance: ");
            double initialBalance1 = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter your mobile number: ");
            String mobileNumber1 = scanner.nextLine();

            bank.createAccount(accountHolderName1, initialBalance1, mobileNumber1);

            System.out.print("Enter account holder name for second account: ");
            String accountHolderName2 = scanner.nextLine();

            System.out.print("Enter initial balance for second account: ");
            double initialBalance2 = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter mobile number for second account: ");
            String mobileNumber2 = scanner.nextLine();

            bank.createAccount(accountHolderName2, initialBalance2, mobileNumber2);

            System.out.print("Enter your account number: ");
            String accno = scanner.nextLine();
            BankAccount account = bank.getAccount(accno);

            int ch;
            do {
                System.out.println("PERFORM ANY OPERATIONS");
                System.out.println("Enter the choice number to perform an operation:");
                System.out.println("1.) Deposit Amount  2.) Amount Withdraw  3.) Transfer Money  4.) Update Account Details  5.) Delete Account  6.) Get All Details  7.) See Transaction History  8.) Exit");

                ch = scanner.nextInt();
                scanner.nextLine();
                switch (ch) {
                    case 1:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                        break;
                    case 2:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawalAmount = scanner.nextDouble();
                        account.withdraw(withdrawalAmount);
                        break;
                    case 3:
                        System.out.print("Enter the receiver's account number: ");
                        String raccno = scanner.nextLine();
                        System.out.print("Enter transfer amount: ");
                        double transferAmount = scanner.nextDouble();
                        BankAccount receiverAccount = bank.getAccount(raccno);
                        if (receiverAccount != null) {
                            account.transfer(receiverAccount, transferAmount);
                        } else {
                            System.out.println("Receiver's account not found.");
                        }
                        break;
                    case 4:
                        bank.updateAccountDetails(accno);
                        break;
                    case 5:
                        bank.deleteAccount(accno);
                        break;
                    case 6:
                        bank.printAccountDetails(accno);
                        break;
                    case 7:
                        List<String> transactionHistory = account.getTransactionHistory();
                        System.out.println("Transaction History: ");
                        for (String transaction : transactionHistory) {
                            System.out.println(transaction);
                        }
                        break;
                    case 8:
                        System.out.println("Thank you!");
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } while (ch != 8);

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data type.");
        } catch (NullPointerException e) {
            System.out.println("Operation failed. Account may not exist.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
