package com.nfcaceres.transactionservice.application.adapters;

import com.nfcaceres.transactionservice.domain.models.Transaction;
import com.nfcaceres.transactionservice.domain.models.TransactionEventPhase;
import com.nfcaceres.transactionservice.domain.models.TransactionType;

import java.util.Optional;

public interface ITransactionService {

    Transaction saveTransaction(Transaction transaction);
    boolean postEvent(long transactionId, TransactionEventPhase transactionEventPhase, TransactionType transactionType);
    Optional<Transaction> findById(long transactionId);
}
