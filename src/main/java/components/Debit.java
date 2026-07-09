package components;

// 1.3.3 Creation of the Transfert, Credit, Debit classes
public class Debit extends Flow {
    public Debit(String comment, int identifier, double amount, int targetAccountNumber, boolean effect) {
        super(comment, identifier, amount, targetAccountNumber, effect);
    }
    public Debit() {}
}