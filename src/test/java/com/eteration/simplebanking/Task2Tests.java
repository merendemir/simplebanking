package com.eteration.simplebanking;


import com.eteration.simplebanking.controller.AccountController;
import com.eteration.simplebanking.controller.AccountRestController;
import com.eteration.simplebanking.dto.TransactionRequestDto;
import com.eteration.simplebanking.dto.TransactionResponseDto;
import com.eteration.simplebanking.enumeration.TransactionStatus;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.transaction.impl.DepositTransaction;
import com.eteration.simplebanking.model.transaction.impl.WithdrawalTransaction;
import com.eteration.simplebanking.services.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
public class Task2Tests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new AccountRestController(accountService))
                .build();
    }

    @Test
    public void testCreditAccount() throws Exception {
        // given
        Account testAccount = getTestAccount();
        TransactionRequestDto requestDto = new TransactionRequestDto(1000.0);
        UUID approvalCode = UUID.randomUUID();
        TransactionResponseDto responseDto = new TransactionResponseDto(TransactionStatus.OK, approvalCode);

        // when
        when(accountService.credit(testAccount.getAccountNumber(), requestDto)).thenReturn(responseDto);

        // then
        mockMvc.perform(post("/account/v1/credit/" + testAccount.getAccountNumber())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.approvalCode").value(approvalCode.toString()));
    }


    @Test
    public void testDebitAccount() throws Exception {
        // given
        Account testAccount = getTestAccount();
        TransactionRequestDto requestDto = new TransactionRequestDto(50.0);
        UUID approvalCode = UUID.randomUUID();
        TransactionResponseDto responseDto = new TransactionResponseDto(TransactionStatus.OK, approvalCode);

        // when
        when(accountService.debit(testAccount.getAccountNumber(), requestDto)).thenReturn(responseDto);

        // then
        mockMvc.perform(post("/account/v1/debit/" + testAccount.getAccountNumber())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.approvalCode").value(approvalCode.toString()));
    }

    @Test
    public void testGetAccount() throws Exception {
        // given
        Account testAccount = getTestAccount();
        DepositTransaction testDeposit = new DepositTransaction(1000.0);
        WithdrawalTransaction testWithdraw = new WithdrawalTransaction(50.0);

        testAccount.post(testDeposit);
        testAccount.post(testWithdraw);

        // when
        when(accountService.findAccount(testAccount.getAccountNumber())).thenReturn(testAccount);

        mockMvc.perform(get("/account/v1/" + testAccount.getAccountNumber()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber").value(testAccount.getAccountNumber()))
                .andExpect(jsonPath("$.owner").value(testAccount.getOwner()))
                .andExpect(jsonPath("$.balance").value(testDeposit.getAmount() - testWithdraw.getAmount()))
                .andExpect(jsonPath("$.createDate").isNotEmpty())
                .andExpect(jsonPath("$.transactions.length()").value(2))

                .andExpect(jsonPath("$.transactions[0].date").isNotEmpty())
                .andExpect(jsonPath("$.transactions[0].amount").value(testDeposit.getAmount()))
                .andExpect(jsonPath("$.transactions[0].transactionType").value(testDeposit.getTransactionType().toString()))
                .andExpect(jsonPath("$.transactions[0].approvalCode").value(testDeposit.getApprovalCode().toString()))
                .andExpect(jsonPath("$.transactions[1].date").isNotEmpty())
                .andExpect(jsonPath("$.transactions[1].amount").value(testWithdraw.getAmount()))
                .andExpect(jsonPath("$.transactions[1].transactionType").value(testWithdraw.getTransactionType().toString()))
                .andExpect(jsonPath("$.transactions[1].approvalCode").value(testWithdraw.getApprovalCode().toString()))
        ;
        // then

    }

    private Account getTestAccount() {
        return new Account("Kerem Karaca", "669-7788");
    }

}
