package com.nfcaceres.transactionservice.application.execeptions;

import java.io.IOException;

public class TransactionPostEventExction extends IOException {

    public TransactionPostEventExction(String message) {
        super(message);
    }
}
