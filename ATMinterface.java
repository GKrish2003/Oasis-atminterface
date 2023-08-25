import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

class Account {
    private String accountNumber;
    private double balance;
    private List<Transaction> transactionHistory;

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount));
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount));
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public void transfer(Account recipient, double amount) {
        if (amount <= balance) {
            balance -= amount;
            recipient.deposit(amount);
            transactionHistory.add(new Transaction("Transfer", amount));
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public void displayTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction.getType() + ": " + transaction.getAmount());
        }
        System.out.println("Current balance: " + balance);
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        Account account = new Account("123456789", 1000.0);

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            System.out.println("ATM Interface");
            System.out.println("Type 1 - Deposit");
            System.out.println("Type 2 - Withdraw");
            System.out.println("Type 3 - Transfer");
            System.out.println("Type 4 - Transaction History");
            System.out.println("Type 5 - Quit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Deposit successful!");
                    break;
                case 2:
                    System.out.print("Enter the withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    account.withdraw(withdrawalAmount);
                    System.out.println("Withdrawal successful!");
                    break;
                case 3:
                    System.out.print("Enter the recipient's account number: ");
                    String recipientAccountNumber = scanner.next();
                    System.out.print("Enter the transfer amount: ");
                    double transferAmount = scanner.nextDouble();
                    Account recipientAccount = new Account(recipientAccountNumber, 0);
                    account.transfer(recipientAccount, transferAmount);
                    System.out.println("Transfer successful!");
                    break;
                case 4:
                    account.displayTransactionHistory();
                    break;
                case 5:
                    quit = true;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();
        }
        scanner.close();
    }
}