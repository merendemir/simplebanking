package com.eteration.simplebanking.model;

import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.transaction.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Account {
    private String accountNumber;
    private String owner;
    private double balance = 0.0;
    private Date createDate;
    private final List<Transaction> transactions = new ArrayList<>();

    public Account() {
        this.createDate = new Date();
    }

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.createDate = new Date();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void credit(double amount) {
        this.balance += amount;
    }

    public void debit(double amount) throws InsufficientBalanceException {
        if (this.balance < amount) {
            throw new InsufficientBalanceException();
        }

        this.balance -= amount;
    }

    public void post(Transaction transaction) throws Exception {
        transaction.apply(this);
        transactions.add(transaction);
    }
}
