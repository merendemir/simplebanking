package com.eteration.simplebanking.model.transaction.impl;

import com.eteration.simplebanking.enumeration.TransactionType;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.transaction.Transaction;

public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction(double amount) {
        super(amount, TransactionType.WITHDRAWAL);
    }

    @Override
    public void apply(Account account) throws InsufficientBalanceException {
        account.debit(getAmount());
        generateApprovalCode();
    }

    @Override
    public String toString() {
        return "WithdrawalTransaction{" +
                super.toString() +
                '}';
    }
}


