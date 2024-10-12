package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.enumeration.TransactionStatus;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.transaction.impl.DepositTransaction;
import com.eteration.simplebanking.model.transaction.impl.WithdrawalTransaction;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    public ResponseEntity<Account> getAccount(String accountNumber) {
        return ResponseEntity.ok(accountService.findAccount(accountNumber));
    }

    public ResponseEntity<TransactionStatus> credit(String accountNumber, DepositTransaction transaction) {
        Account account = accountService.findAccount(accountNumber);
        account.credit(transaction.getAmount());
        return ResponseEntity.ok(TransactionStatus.OK);
    }

    public ResponseEntity<TransactionStatus> debit(String accountNumber, WithdrawalTransaction transaction) throws InsufficientBalanceException {
        Account account = accountService.findAccount(accountNumber);
        account.debit(transaction.getAmount());
        AccountRepository.save(account);
        return ResponseEntity.ok(TransactionStatus.OK);
    }
}