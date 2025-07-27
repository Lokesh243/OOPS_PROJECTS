import java.util.Scanner;

// Abstract class for abstraction
abstract class Account {
    private String accountNumber;
    protected double balance; // protected so subclasses can access

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    // Encapsulated accessors
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    // Common operations
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: ₹" + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    // Abstract method to enforce different withdrawal rules
    public abstract void withdraw(double amount);
}

// Subclass: SavingsAccount
class SavingsAccount extends Account {
    private double withdrawalLimit = 2000; // specific rule

    public SavingsAccount(String accountNumber, double initialBalance) {
        super(accountNumber, initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance && amount <= withdrawalLimit) {
            balance -= amount;
            System.out.println("Withdrew: ₹" + amount);
        } else {
            System.out.println("Withdrawal failed: Check amount or limit!");
        }
    }
}

// Subclass: CurrentAccount
class CurrentAccount extends Account {
    public CurrentAccount(String accountNumber, double initialBalance) {
        super(accountNumber, initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: ₹" + amount);
        } else {
            System.out.println("Withdrawal failed: Insufficient funds!");
        }
    }
}

// ATM class using polymorphism
class ATM {
    private Account account; // can hold SavingsAccount or CurrentAccount

    public ATM(Account account) {
        this.account = account;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Balance: ₹" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount: ");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount: ");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using ATM!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 4);

        sc.close();
    }
}

// Main class
public class MiniAtmSystem {
    public static void main(String[] args) {
        // Create an account (polymorphism in action)
        Account acc = new SavingsAccount("123456", 5000);
        ATM atm = new ATM(acc);
        atm.start();
    }
}
