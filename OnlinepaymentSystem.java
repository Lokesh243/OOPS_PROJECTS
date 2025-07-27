// Abstract class for abstraction
abstract class Payment {
    protected double amount;

    Payment(double amount) {
        this.amount = amount;
    }

    // Abstract method: each payment type implements differently
    abstract void processPayment(UserAccount account);
}

// Concrete class 1
class CreditCardPayment extends Payment {
    CreditCardPayment(double amount) {
        super(amount);
    }

    @Override
    void processPayment(UserAccount account) {
        System.out.println("Processing credit card payment of ₹" + amount);
        account.withdraw(amount);
    }
}

// Concrete class 2
class UPIPayment extends Payment {
    UPIPayment(double amount) {
        super(amount);
    }

    @Override
    void processPayment(UserAccount account) {
        System.out.println("Processing UPI payment of ₹" + amount);
        account.withdraw(amount);
    }
}

// Encapsulated class
class UserAccount {
    private String username;
    private double balance;

    public UserAccount(String username, double initialBalance) {
        this.username = username;
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        }
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("₹" + amount + " debited. Remaining balance: ₹" + balance);
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }
}

// Main class
public class OnlinepaymentSystem {
    public static void main(String[] args) {
        UserAccount user = new UserAccount("Lokesh", 5000);

        Payment p1 = new CreditCardPayment(1500);
        p1.processPayment(user);

        Payment p2 = new UPIPayment(2000);
        p2.processPayment(user);

        System.out.println("Final balance: ₹" + user.getBalance());
    }
}
