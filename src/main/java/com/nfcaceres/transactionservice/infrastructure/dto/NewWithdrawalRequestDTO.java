package com.nfcaceres.transactionservice.infrastructure.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NewWithdrawalRequestDTO {
    private long acountId;
    private BigDecimal ammount;
}
