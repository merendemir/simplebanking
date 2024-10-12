package com.eteration.simplebanking.model.transaction;

import com.eteration.simplebanking.enumeration.TransactionType;
import com.eteration.simplebanking.model.Account;

import java.util.Date;
import java.util.UUID;

public abstract class Transaction {
    private final Date date;
    private final double amount;
    private final TransactionType type;
    private UUID approvalCode;

    public Transaction(double amount, TransactionType type) {
        this.amount = amount;
        this.type = type;
        this.date = new Date();
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public UUID getApprovalCode() {
        return approvalCode;
    }

    public TransactionType getTransactionType() {
        return type;
    }

    public void generateApprovalCode() {
        this.approvalCode = UUID.randomUUID();
    }

    public abstract void apply(Account account) throws Exception;

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", date=" + date +
                '}';
    }
}
