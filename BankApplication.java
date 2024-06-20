import java.util.*;

class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private String mobileNumber;
    private List<String> transactionHistory;

    public BankAccount(String accountHoldername,double initialBalance,String mobileNumber)
    {
        this.accountNumber = generateAccountNumber();
        this.accountHolderName = accountHoldername;
        this.balance = initialBalance;
        this.mobileNumber = mobileNumber;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with balance "+ initialBalance);
    }

    private String generateAccountNumber()
    {
        Random random = new Random();
        StringBuilder accountNumberBuilder = new StringBuilder();
        for(int i=0;i<11;i++)
        {
            accountNumberBuilder.append(random.nextInt(10));
        }

        return accountNumberBuilder.toString();
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public String getAccountHolderName()
    {
        return accountHolderName;
    }

    public double getBalance()
    {
        return balance;
    }

    public String getMobileNumber()
    {
        return mobileNumber;
    }

    public List<String> getTransactionHistory()
    {
        return transactionHistory;
    }

    public void deposit(double amount)
    {
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
        transactionHistory.add("Account holder mobileNumber changed to: " + newNumber);
    }
}

class Bank{
    private Map<String,BankAccount> accounts;

    public Bank()
    {
        accounts = new HashMap<>();
    }

    public void createAccount(String accountHolderName, double initialBalance , String mobileNumber)
    {
        BankAccount newAccount = new BankAccount(accountHolderName, initialBalance , mobileNumber);
        accounts.put(newAccount.getAccountNumber(),newAccount);
        System.out.println("Account created successfully with Account Number: " + newAccount.getAccountNumber());

    }

    public void deleteAccount(String accountNumber)
    {
        if(accounts.containsKey(accountNumber))
        {
            accounts.remove(accountNumber);
            System.out.println("Account deleted successfully");

        }
        else{
            System.out.println("Account not found");
        }
    }

    public void updateAccountdetails(String accountNumber)
    {
        System.out.println("Which field you want to update?");
        System.out.println("1.) AccountHolderName 2.) linked MobileNumber");
        Scanner o = new Scanner(System.in);
        int choice = o.nextInt();
        o.nextLine();
        switch(choice)
        {
            case 1: System.out.println("Enter the new AccountHolderName");
                    String newaccountHolderName = o.nextLine();
                    if(accounts.containsKey(accountNumber))
                    {
                        BankAccount account = accounts.get(accountNumber);
                        account.updateAccountHolderName(newaccountHolderName);
                    }
                    else{
                        System.out.println("Account not found");
                    }
                    break;

            case 2: System.out.println("Enter the new Mobile Number");
                    String newmobileNumber = o.nextLine();
                    if(accounts.containsKey(accountNumber))
                    {
                        BankAccount account = accounts.get(accountNumber);
                        account.updateMobileNumber(newmobileNumber);
                    }
                    else{
                        System.out.println("Account not found");
                    }
                    break;

            default: System.out.println("invalid choice");
                     break;
        }

    }

    public BankAccount getAccount(String accountNumber)
    {
        BankAccount account = accounts.get(accountNumber);
        return account;
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

        System.out.print("Enter account holder name: ");
        String accountHolderName1 = scanner.nextLine();

        System.out.print("Enter initial balance: ");
        double initialBalance1 = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter your mobile number: ");
        String mobileNumber1 = scanner.nextLine();

        bank.createAccount(accountHolderName1, initialBalance1, mobileNumber1);

        System.out.print("Enter account holder name for second account: ");
        String accountHolderName2 = scanner.nextLine();

        System.out.print("Enter initial balance for second account: ");
        double initialBalance2 = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter mobile number for second account: ");
        String mobileNumber2 = scanner.nextLine();

        bank.createAccount(accountHolderName2, initialBalance2, mobileNumber2);

        int ch;
        System.out.println("PERform any operations ");
        System.out.println("enter your account number ");
        String accno = scanner.nextLine();
        BankAccount account =  bank.getAccount(accno);
        System.out.println("Enter the choice number to perform an operation: ");
        do{
        System.out.println("1.) Deposit Amount  2.) Amount withdraw  3.) transfer money  4.)Update Accountdetails  5.)delete Account  6.)Getalldetails  7.)see transactionhistory  8.)Exit");
        ch = scanner.nextInt();
        scanner.nextLine();
        switch(ch)
        {
            case 1: System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;

            case 2: System.out.print("Enter withdrawl amount: ");
                    double withdrawlAmount = scanner.nextDouble();
                    account.withdraw(withdrawlAmount);
                    break;

            case 3: System.out.println("Enter the receiver's account number: ");
                    String raccno = scanner.nextLine();
                    System.out.print("Enter transfer amount: ");
                    double transferAmount = scanner.nextDouble();
                    BankAccount receiveraccno = bank.getAccount(raccno);
                    account.transfer(receiveraccno, transferAmount);
                    break;

            case 4: bank.updateAccountdetails(accno);
                    break;

            case 5: bank.deleteAccount(accno);
                    break;

            case 6: bank.printAccountDetails(accno);
                    break;

            case 7: List<String> transactionHistory = account.getTransactionHistory();
                    System.out.println("Transaction History: ");
                    for (String transaction : transactionHistory) {
                        System.out.println(transaction);
                    }
                    break;
            
            case 8: System.out.println("Thankyou!!");
                    break;
        }
    }while(ch!=8);


        scanner.close();
    }
}
