package org.example;
import java.util.*;
public class Main {
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

            String accountno1= bank.createAccount(accountHolderName1, initialBalance1, mobileNumber1);

            System.out.print("Enter account holder name for second account: ");
            String accountHolderName2 = scanner.nextLine();

            System.out.print("Enter initial balance for second account: ");
            double initialBalance2 = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter mobile number for second account: ");
            String mobileNumber2 = scanner.nextLine();

            String accountno2= bank.createAccount(accountHolderName2, initialBalance2, mobileNumber2);

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

        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}