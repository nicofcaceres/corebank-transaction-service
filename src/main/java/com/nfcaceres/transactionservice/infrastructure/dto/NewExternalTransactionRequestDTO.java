package com.nfcaceres.transactionservice.infrastructure.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NewExternalTransactionRequestDTO {
    private long fromAccountId;
    private BigDecimal amount;
    private String externalBankId;
    private String externalAccountNumber;
}
