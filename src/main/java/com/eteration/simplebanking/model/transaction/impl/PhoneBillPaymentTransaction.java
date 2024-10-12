package com.eteration.simplebanking.model.transaction.impl;

import com.eteration.simplebanking.enumeration.TransactionType;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.transaction.Transaction;

public class PhoneBillPaymentTransaction extends Transaction {
    private final String payee;
    private final String phoneNumber;

    public PhoneBillPaymentTransaction(String payee, String phoneNumber, double amount) {
        super(amount, TransactionType.PHONE_BILL_PAYMENT);
        this.payee = payee;
        this.phoneNumber = phoneNumber;
    }

    public String getPayee() {
        return payee;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void apply(Account account) throws InsufficientBalanceException {
        account.debit(getAmount());
        System.out.println("Paid phone bill to " + payee + " for number " + phoneNumber);
        generateApprovalCode();
    }

    @Override
    public String toString() {
        return "PhoneBillPaymentTransaction{" +
                super.toString() +
                "payee='" + payee + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}