package com.nfcaceres.transactionservice.application.mapper;


import com.nfcaceres.transactionservice.application.data.TransactionDTO;
import com.nfcaceres.transactionservice.domain.models.Transaction;
import com.nfcaceres.transactionservice.domain.models.TransactionCaracter;
import com.nfcaceres.transactionservice.domain.models.TransactionStatus;
import com.nfcaceres.transactionservice.domain.models.TransactionType;

public class TransactionMapper {

    public static TransactionDTO toTransactionDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setTransactionType(transaction.getTransactionType().name());
        dto.setCaracter(transaction.getCaracter().name());
        dto.setStatus(transaction.getStatus().name());
        dto.setAmount(transaction.getAmount());
        dto.setFromAccountId(transaction.getFromAccountId());
        dto.setToAccountId(transaction.getToAccountId());
        dto.setExternalBankId(transaction.getExternalBankId());
        dto.setExternalAccountNumber(transaction.getExternalAccountNumber());
        dto.setCreatedAt(transaction.getCreatedAt());
        dto.setUpdatedAt(transaction.getUpdatedAt());
        return dto;
    }

    public static Transaction toTransactionEntity(TransactionDTO dto) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.valueOf(dto.getTransactionType()));
        transaction.setCaracter(TransactionCaracter.valueOf(dto.getCaracter()));
        transaction.setStatus(TransactionStatus.valueOf(dto.getStatus()));
        transaction.setAmount(dto.getAmount());
        transaction.setFromAccountId(dto.getFromAccountId());
        transaction.setToAccountId(dto.getToAccountId());
        transaction.setExternalBankId(dto.getExternalBankId());
        transaction.setExternalAccountNumber(dto.getExternalAccountNumber());
        transaction.setCreatedAt(dto.getCreatedAt());
        transaction.setUpdatedAt(dto.getUpdatedAt());
        return transaction;
    }
}