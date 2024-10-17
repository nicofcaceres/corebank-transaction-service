package com.nfcaceres.transactionservice.infrastructure.controllers;

import com.nfcaceres.transactionservice.application.data.TransactionDTO;
import com.nfcaceres.transactionservice.application.usecases.CompleteTransactionUC;
import com.nfcaceres.transactionservice.application.usecases.CreateDepositUC;
import com.nfcaceres.transactionservice.application.usecases.CreateExternalTransactionUC;
import com.nfcaceres.transactionservice.application.usecases.CreateInternalTransactionUC;
import com.nfcaceres.transactionservice.application.usecases.CreateWithdrawalUC;
import com.nfcaceres.transactionservice.application.usecases.DeclineTransactionUC;
import com.nfcaceres.transactionservice.infrastructure.dto.NewDepositTransactionDTO;
import com.nfcaceres.transactionservice.infrastructure.dto.NewExternalTransactionRequestDTO;
import com.nfcaceres.transactionservice.infrastructure.dto.NewInternalTransactionRequestDTO;
import com.nfcaceres.transactionservice.infrastructure.dto.NewWithdrawalRequestDTO;
import com.nfcaceres.transactionservice.infrastructure.service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/new/withdrawal", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDTO> newWithdrawal(HttpServletRequest request, NewWithdrawalRequestDTO newWithdrawalRequestDTO) {
        CreateWithdrawalUC createWithdrawalUC = new CreateWithdrawalUC(transactionService);

        TransactionDTO transactionDTO = createWithdrawalUC.execute(newWithdrawalRequestDTO.getAccountId(), newWithdrawalRequestDTO.getAmount());
        return ResponseEntity.ok(transactionDTO);
    }
    @PostMapping(value = "/new/deposit", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDTO> newDeposit(HttpServletRequest request, NewDepositTransactionDTO newDepositTransactionDTO) {
        CreateDepositUC createDepositUC = new CreateDepositUC(transactionService);
        TransactionDTO transactionDTO = createDepositUC.execute(newDepositTransactionDTO.getAccountId(),newDepositTransactionDTO.getAmount());
        return ResponseEntity.ok(transactionDTO);
    }
    @PostMapping(value = "/new/ineternal-transaction", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDTO> newInternalTransaction(HttpServletRequest request, NewInternalTransactionRequestDTO newInternalTransactionRequestDTO) {
        CreateInternalTransactionUC createInternalTransactionUC = new CreateInternalTransactionUC(transactionService);
        TransactionDTO transactionDTO = createInternalTransactionUC.execute(
                newInternalTransactionRequestDTO.getFromAccountId(),
                newInternalTransactionRequestDTO.getToAccountId(),
                newInternalTransactionRequestDTO.getAmount());
        return ResponseEntity.ok(transactionDTO);
    }
    @PostMapping(value = "/new/external-transaction", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDTO> newExternalTransaction(HttpServletRequest request, NewExternalTransactionRequestDTO newExternalTransactionRequestDTO) {
        CreateExternalTransactionUC createExternalTransactionUC = new CreateExternalTransactionUC(transactionService);
        TransactionDTO transactionDTO = createExternalTransactionUC.execute(
                newExternalTransactionRequestDTO.getFromAccountId(),
                newExternalTransactionRequestDTO.getExternalBankId(),
                newExternalTransactionRequestDTO.getExternalAccountNumber(),
                newExternalTransactionRequestDTO.getAmount()
        );
        return ResponseEntity.ok(transactionDTO);
    }

    @PostMapping(value = "/complete/{transactionId}", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDTO> complete(HttpServletRequest request, @PathVariable("transactionId") long transactionId) {
        CompleteTransactionUC completeTransactionUC = new CompleteTransactionUC(transactionService);
        TransactionDTO transactionDTO = completeTransactionUC.execute(transactionId);

        return ResponseEntity.ok(transactionDTO);
    }
    @PostMapping(value = "/decline/{transactionId}", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDTO> decline(HttpServletRequest request, @PathVariable("transactionId") long transactionId) {
        DeclineTransactionUC completeTransactionUC = new DeclineTransactionUC(transactionService);
        TransactionDTO transactionDTO = completeTransactionUC.execute(transactionId);

        return ResponseEntity.ok(transactionDTO);
    }


}
