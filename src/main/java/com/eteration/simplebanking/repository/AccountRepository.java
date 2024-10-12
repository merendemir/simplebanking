package com.eteration.simplebanking.repository;

import com.eteration.simplebanking.model.Account;

import java.util.HashMap;


/**
 * Project: simplebanking
 * Package: com.eteration.simplebanking
 *
 * @author eren
 * Created at: 12.10.2024 12:29
 */
public class AccountRepository extends HashMap<String, Account> {

    private static AccountRepository instance;

    private AccountRepository() {
    }

    public static AccountRepository getInstance() {
        if (instance == null) {
            synchronized (AccountRepository.class) {
                if (instance == null) {
                    instance = new AccountRepository();
                }
            }
        }
        return instance;
    }

    public static Account findByAccountNumber(String accountNumber) {
        return getInstance().get(accountNumber);
    }

    public static void save(Account account) {
        getInstance().put(account.getAccountNumber(), account);
    }
}
