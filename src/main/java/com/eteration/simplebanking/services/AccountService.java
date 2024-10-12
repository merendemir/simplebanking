package com.eteration.simplebanking.services;

import com.eteration.simplebanking.dto.TransactionRequestDto;
import com.eteration.simplebanking.dto.TransactionResponseDto;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.transaction.impl.DepositTransaction;
import com.eteration.simplebanking.model.transaction.impl.WithdrawalTransaction;
import com.eteration.simplebanking.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    public Account findAccount(String accountNumber) {
        return AccountRepository.findByAccountNumber(accountNumber);
    }

    public TransactionResponseDto credit(String accountNumber, TransactionRequestDto transactionRequestDto) {
        Account account = findAccount(accountNumber);

        DepositTransaction depositTransaction = new DepositTransaction(transactionRequestDto.getAmount());
        depositTransaction.apply(account);
        AccountRepository.save(account);

        return TransactionResponseDto.success(depositTransaction.getApprovalCode());
    }

    public TransactionResponseDto debit(String accountNumber, TransactionRequestDto transactionRequestDto) {
        try {
            Account account = findAccount(accountNumber);

            WithdrawalTransaction withdrawalTransaction = new WithdrawalTransaction(transactionRequestDto.getAmount());
            withdrawalTransaction.apply(account);
            AccountRepository.save(account);

            return TransactionResponseDto.success(withdrawalTransaction.getApprovalCode());

        } catch (Exception e) {
            return TransactionResponseDto.failed();
        }
    }

}
