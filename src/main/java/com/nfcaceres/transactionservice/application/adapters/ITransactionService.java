package com.nfcaceres.transactionservice.application.adapters;

import com.nfcaceres.transactionservice.domain.models.Transaction;
import com.nfcaceres.transactionservice.domain.models.TransactionEventPhase;

public interface ITransactionService {

    Transaction saveTransaction(Transaction transaction);
    boolean postEvent(long transactionId, TransactionEventPhase transactionEventPhase);
}
