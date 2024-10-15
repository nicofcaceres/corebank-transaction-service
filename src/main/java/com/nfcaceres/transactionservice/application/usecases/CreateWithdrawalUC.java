package com.nfcaceres.transactionservice.application.usecases;

import com.nfcaceres.transactionservice.application.adapters.ITransactionService;
import com.nfcaceres.transactionservice.application.data.TransactionDTO;
import com.nfcaceres.transactionservice.application.mapper.TransactionMapper;
import com.nfcaceres.transactionservice.domain.models.Transaction;
import com.nfcaceres.transactionservice.domain.models.TransactionCaracter;
import com.nfcaceres.transactionservice.domain.models.TransactionEventPhase;
import com.nfcaceres.transactionservice.domain.models.TransactionStatus;
import com.nfcaceres.transactionservice.domain.models.TransactionType;

import java.math.BigDecimal;

public class CreateWithdrawalUC {

    private final ITransactionService transactionService;

    public CreateWithdrawalUC(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public TransactionDTO execute(Long fromAccountId, BigDecimal amount) {
        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.WITHDRAWAL)
                .caracter(TransactionCaracter.DEBIT)
                .fromAccountId(fromAccountId)
                .eventPhase(TransactionEventPhase.INITIATED)
                .amount(amount)
                .build();
        transaction = transactionService.saveTransaction(transaction);
        boolean published = transactionService.postEvent(transaction.getId(), transaction.getEventPhase());
        if (!published){
            transaction.setStatus(TransactionStatus.FAILED);
            transaction = transactionService.saveTransaction(transaction);
        }
        return TransactionMapper.toTransactionDTO(transaction);
    }
}
