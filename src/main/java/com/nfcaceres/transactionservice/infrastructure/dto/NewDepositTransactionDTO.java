package com.nfcaceres.transactionservice.infrastructure.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NewDepositTransactionDTO {

    private long accountId;
    private BigDecimal amount;
}
