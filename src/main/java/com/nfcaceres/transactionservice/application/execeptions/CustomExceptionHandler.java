package com.nfcaceres.transactionservice.application.execeptions;

import com.nfcaceres.transactionservice.application.data.TransactionDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2
public class CustomExceptionHandler {

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<TransactionDTO> handleTransactionNotFoundException(TransactionNotFoundException ex) {
        // TODO: se debe extender el manejo de la excecion al sistema adecuado de registro de errores
        log.error(ex);
        return  ResponseEntity.notFound().build();
    }
}
