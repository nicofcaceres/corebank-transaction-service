package com.nfcaceres.transactionservice.infrastructure.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NewInternalTransactionRequestDTO {
    private long fromAccountId;
    private Long toAccountId;
    private BigDecimal amount;
}
