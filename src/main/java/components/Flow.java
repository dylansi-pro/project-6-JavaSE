package components;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDate;

// 1.3.2 Creation of the Flow class
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Credit.class, name = "Credit"),
        @JsonSubTypes.Type(value = Debit.class, name = "Debit"),
        @JsonSubTypes.Type(value = Transfert.class, name = "Transfert")
})
public class Flow {
    private String comment;
    private int identifier;
    private double amount;
    private int targetAccountNumber;
    private boolean effect;
    private LocalDate date;

    public Flow(String comment, int identifier, double amount, int targetAccountNumber, boolean effect) {
        this.comment = comment;
        this.identifier = identifier;
        this.amount = amount;
        this.targetAccountNumber = targetAccountNumber;
        this.effect = effect;
        this.date = LocalDate.now();
    }

    public Flow() {

    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getIdentifier() {
        return identifier;
    }
    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getTargetAccountNumber() {
        return targetAccountNumber;
    }
    public void setTargetAccountNumber(int targetAccountNumber) {
        this.targetAccountNumber = targetAccountNumber;
    }

    public boolean isEffect() {
        return effect;
    }
    public void setEffect(boolean effect) {
        this.effect = effect;
    }

    public LocalDate getDate() {
        return date;
    }
}
