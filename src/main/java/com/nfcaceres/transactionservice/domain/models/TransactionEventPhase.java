package com.nfcaceres.transactionservice.domain.models;

public enum TransactionEventPhase {
    INITIATED,     // La transacción ha sido iniciada
    VALIDATED,     // La transacción ha sido validada (ej. se han comprobado fondos o destinatarios)
    APPROVED,      // La transacción ha sido aprobada (está lista para ser procesada)
    COMPLETED,     // La transacción ha sido completada con éxito
    FAILED,        // La transacción ha fallado
    REVERSED,      // La transacción ha sido revertida
    COMPENSATION_INITIATED, // Se ha iniciado un proceso de compensación por fallo
    COMPENSATED    // La compensación ha sido completada
}