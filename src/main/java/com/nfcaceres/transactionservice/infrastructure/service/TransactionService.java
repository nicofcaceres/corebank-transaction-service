package com.nfcaceres.transactionservice.infrastructure.service;

import com.nfcaceres.transactionservice.application.adapters.ITransactionService;
import com.nfcaceres.transactionservice.domain.models.Transaction;
import com.nfcaceres.transactionservice.domain.models.TransactionEventPhase;
import com.nfcaceres.transactionservice.domain.models.TransactionType;
import com.nfcaceres.transactionservice.infrastructure.dto.TransactionEventDTO;
import com.nfcaceres.transactionservice.infrastructure.messaging.EventBridgeBaseSender;
import com.nfcaceres.transactionservice.infrastructure.persistence.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;
    private final EventBridgeBaseSender eventBridgeBaseSender;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, EventBridgeBaseSender eventBridgeBaseSender) {
        this.transactionRepository = transactionRepository;
        this.eventBridgeBaseSender = eventBridgeBaseSender;
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public boolean postEvent(long transactionId, TransactionEventPhase transactionEventPhase, TransactionType transactionType) {
        TransactionEventDTO transactionEventDTO = TransactionEventDTO
                .builder()
                .transactionId(transactionId)
                .transactionType(transactionType.name())
                .build();
        return eventBridgeBaseSender.sendEvent(transactionEventDTO,transactionEventPhase.name() );
    }

    @Override
    public Optional<Transaction> findById(long transactionId) {
        return transactionRepository.findById(transactionId);
    }
}
