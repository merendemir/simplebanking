package com.eteration.simplebanking.model.transaction.impl;

import com.eteration.simplebanking.enumeration.TransactionType;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.transaction.Transaction;

public class CheckTransaction extends Transaction {
    private final String checkNumber;

    public CheckTransaction(String checkNumber, double amount) {
        super(amount, TransactionType.CHECK_PAYMENT);
        this.checkNumber = checkNumber;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    @Override
    public void apply(Account account) throws InsufficientBalanceException {
        account.debit(getAmount());
        generateApprovalCode();
        System.out.println("Paid check with number " + checkNumber);
    }

    @Override
    public String toString() {
        return "CheckTransaction{" +
                super.toString() +
                "checkNumber='" + checkNumber + '\'' +
                '}';
    }
}