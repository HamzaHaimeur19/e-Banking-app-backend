package com.example.ebankingbackend.exceptions;

public class BalanceNotSufficientException extends RuntimeException {
    public BalanceNotSufficientException(String balanceNotSufficientException) {
        super(balanceNotSufficientException);
    }
}
