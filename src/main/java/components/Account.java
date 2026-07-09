package components;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

// 1.2.1 Creation of the account class
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CurrentAccount.class, name = "CurrentAccount"),
        @JsonSubTypes.Type(value = SavingsAccount.class, name = "SavingsAccount")
})
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

    public Account() {
        // Constructeur vide requis par Jackson
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
        String clientInfo = (this.client != null) ? String.valueOf(this.client.getClientNumber()) : "Aucun client";
        return "Compte n°" + this.accountNumber + " | Client : " + clientInfo + " | Solde : " + this.balance;
    }

    public void processFlow(Flow flow) {
        if (flow instanceof Credit) {
            this.balance += flow.getAmount();
        } else if (flow instanceof Debit) {
            this.balance -= flow.getAmount();
        } else if (flow instanceof Transfert t) {
            if (this.accountNumber == t.getTargetAccountNumber()) {
                this.balance += t.getAmount();
                System.out.println("-> Crédit effectué sur compte " + this.accountNumber);
            } else if (this.accountNumber == t.getOriginAccountNumber()) {
                this.balance -= t.getAmount();
                System.out.println("-> Débit effectué sur compte " + this.accountNumber);
            }
        }
    }

    // Génération client
    public void setClient(Client client) {
        this.client = client;
    }
}
