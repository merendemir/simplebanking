package com.eteration.simplebanking.model.transaction.impl;

import com.eteration.simplebanking.enumeration.TransactionType;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.transaction.Transaction;

public class DepositTransaction extends Transaction {

    public DepositTransaction(double amount) {
        super(amount, TransactionType.DEPOSIT);
    }

    @Override
    public void apply(Account account) {
        account.credit(getAmount());
        generateApprovalCode();
    }

    @Override
    public String toString() {
        return "DepositTransaction{" +
                super.toString() +
                '}';
    }
}