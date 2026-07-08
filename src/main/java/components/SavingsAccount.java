package components;

// 1.2.2 Creation of the CurrentAccount and SavingsAccount
public class SavingsAccount extends Account {
    public SavingsAccount(String label, double balance, Client client) {
        super(label, balance, client);
    }
}
