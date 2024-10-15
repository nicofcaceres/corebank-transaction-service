package com.nfcaceres.transactionservice.infrastructure.controllers;

import com.nfcaceres.transactionservice.application.data.TransactionDTO;
import com.nfcaceres.transactionservice.application.execeptions.TransactionPostEventExction;
import com.nfcaceres.transactionservice.application.usecases.CreateWithdrawalUC;
import com.nfcaceres.transactionservice.infrastructure.dto.NewWithdrawalRequestDTO;
import com.nfcaceres.transactionservice.infrastructure.service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDTO> newWithdrawal(HttpServletRequest request, NewWithdrawalRequestDTO newWithdrawalRequestDTO) {
        CreateWithdrawalUC createWithdrawalUC = new CreateWithdrawalUC(transactionService);

        TransactionDTO transactionDTO = createWithdrawalUC.execute(newWithdrawalRequestDTO.getAcountId(), newWithdrawalRequestDTO.getAmmount());
        return ResponseEntity.ok(transactionDTO);
    }
}
