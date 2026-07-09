package components;

// 1.3.3 Creation of the Transfert, Credit, Debit classes
public class Transfert extends Flow {
    private int originAccountNumber;

    public Transfert(String comment, int identifier, double amount, int targetAccountNumber, boolean effect, int originAccountNumber) {
        super(comment, identifier, amount, targetAccountNumber, effect);
        this.originAccountNumber = originAccountNumber;
    }

    public int getOriginAccountNumber() {
        return originAccountNumber;
    }

    public void setOriginAccountNumber(int originAccountNumber) {
        this.originAccountNumber = originAccountNumber;
    }
}