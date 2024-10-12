package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.dto.TransactionRequestDto;
import com.eteration.simplebanking.dto.TransactionResponseDto;
import com.eteration.simplebanking.enumeration.TransactionStatus;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.transaction.impl.DepositTransaction;
import com.eteration.simplebanking.model.transaction.impl.WithdrawalTransaction;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account/v1")
public class AccountRestController {

    private final AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.findAccount(accountNumber));
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionResponseDto> credit(@PathVariable String accountNumber,
                                                         @RequestBody TransactionRequestDto requestDto) {
        return ResponseEntity.ok(accountService.credit(accountNumber, requestDto));
    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionResponseDto> debit(@PathVariable String accountNumber,
                                                   @RequestBody TransactionRequestDto requestDto) {
        return ResponseEntity.ok(accountService.debit(accountNumber, requestDto));
    }
}