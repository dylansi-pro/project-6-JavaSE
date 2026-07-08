package components;

// 1.2.1 Creation of the account class
public abstract class Account {
    protected String label;
    protected Double balance;
    protected static int counter = 0;
    protected int accountNumber;
    protected Client client;


    public Account(String label, double balance, Client client) {
        this.label = label;
        this.balance = balance;
        this.accountNumber = counter++;
        this.client = client;
    }

    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public int getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        return "Client [" + client.getClientNumber() + "] : " + client.getFirstName() + " " + client.getName() + " Balance: " + getBalance();
    }
}
