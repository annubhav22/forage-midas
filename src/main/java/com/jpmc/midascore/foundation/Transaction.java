package com.jpmc.midascore.foundation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

    private String senderUsername;
    private String recipientUsername;
    private float amount;

    public Transaction() {}

    public Transaction(String senderUsername, String recipientUsername, float amount) {
        this.senderUsername = senderUsername;
        this.recipientUsername = recipientUsername;
        this.amount = amount;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getRecipientUsername() {
        return recipientUsername;
    }

    public void setRecipientUsername(String recipientUsername) {
        this.recipientUsername = recipientUsername;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction {sender=" + senderUsername +
               ", recipient=" + recipientUsername +
               ", amount=" + amount + "}";
    }
}
