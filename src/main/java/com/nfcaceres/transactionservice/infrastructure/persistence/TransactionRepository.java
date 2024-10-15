package com.nfcaceres.transactionservice.infrastructure.persistence;

import com.nfcaceres.transactionservice.domain.models.Transaction;
import com.nfcaceres.transactionservice.domain.models.TransactionStatus;
import com.nfcaceres.transactionservice.domain.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByTransactionType(TransactionType transactionType);
    List<Transaction> findByStatus(TransactionStatus status);
    List<Transaction> findByTransactionTypeAndStatus(TransactionType transactionType, TransactionStatus status);
}