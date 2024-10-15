package com.nfcaceres.transactionservice.application.data;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    private Long id;
    private String transactionType;
    private String caracter;
    private String status;
    private BigDecimal amount;
    private Long fromAccountId;
    private Long toAccountId;
    private String externalBankId;
    private String externalAccountNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
