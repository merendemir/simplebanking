package com.eteration.simplebanking;


import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.transaction.impl.CheckTransaction;
import com.eteration.simplebanking.model.transaction.impl.DepositTransaction;
import com.eteration.simplebanking.model.transaction.impl.PhoneBillPaymentTransaction;
import com.eteration.simplebanking.model.transaction.impl.WithdrawalTransaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Tests {

    @Test
    public void testTransactionsWithPhoneBillPayment() throws Exception {
        Account account = new Account("Jim", "12345");
        account.post(new DepositTransaction(1000));
        account.post(new WithdrawalTransaction(200));
        account.post(new PhoneBillPaymentTransaction("Vodafone", "5423345566", 96.50));
        assertEquals(account.getBalance(), 703.50, 0.0001);
    }


    @Test
    public void testAllTransactions() throws Exception {
        Account account = new Account("Jim", "12345");
        account.post(new DepositTransaction(1000));
        account.post(new WithdrawalTransaction(200));
        account.post(new PhoneBillPaymentTransaction("Vodafone", "5423345566", 96.50));
        account.post(new CheckTransaction("123365", 100));

        assertEquals(account.getBalance(), 603.50, 0.0001);
    }

}
