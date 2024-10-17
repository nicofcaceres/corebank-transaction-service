package com.nfcaceres.transactionservice.application.usecases;

import com.nfcaceres.transactionservice.application.adapters.ITransactionService;
import com.nfcaceres.transactionservice.application.data.TransactionDTO;
import com.nfcaceres.transactionservice.application.execeptions.TransactionNotFoundException;
import com.nfcaceres.transactionservice.application.mapper.TransactionMapper;
import com.nfcaceres.transactionservice.domain.models.Transaction;
import com.nfcaceres.transactionservice.domain.models.TransactionEventPhase;
import com.nfcaceres.transactionservice.domain.models.TransactionStatus;

import java.util.Optional;

public class DeclineTransactionUC {

    private final ITransactionService transactionService;

    public DeclineTransactionUC(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public TransactionDTO execute(Long transactionId) {
        Optional<Transaction> optionalTransaction = transactionService.findById(transactionId);
        Transaction transaction = optionalTransaction.orElseThrow(() ->
                new TransactionNotFoundException(String.format("No se pudo encontrar la transaccion requerida; %s", transactionId))
        );
        transaction.setStatus(TransactionStatus.FAILED);
        transaction.setEventPhase(TransactionEventPhase.COMPLETED);
        transaction = transactionService.saveTransaction(transaction);

        boolean published = transactionService.postEvent(transaction.getId(), transaction.getEventPhase(),transaction.getTransactionType());
        if (!published){
            transaction = transactionService.saveTransaction(transaction);
        }
        return TransactionMapper.toTransactionDTO(transaction);
    }
}
