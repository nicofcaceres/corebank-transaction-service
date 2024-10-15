package com.nfcaceres.transactionservice.domain.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "transactions",indexes = {
        @Index(name = "idx_transaction_type", columnList = "transaction_type"),
        @Index(name = "idx_from_account_id", columnList = "from_account_id"),
        @Index(name = "idx_to_account_id", columnList = "to_account_id"),
        @Index(name = "idx_transaction_caracter", columnList = "caracter")
})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "caracter", nullable = false)
    private TransactionCaracter caracter;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TransactionStatus status = TransactionStatus.PENDING;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "from_account_id")
    private Long fromAccountId;

    @Column(name = "to_account_id")
    private Long toAccountId;

    @Column(name = "external_bank_id")
    private String externalBankId;

    @Column(name = "external_account_number")
    private String externalAccountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_phase")
    private TransactionEventPhase eventPhase;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

}
