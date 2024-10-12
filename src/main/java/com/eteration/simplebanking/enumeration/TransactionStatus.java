package com.eteration.simplebanking.enumeration;


public enum TransactionStatus {
    OK("OK"),
    FAILED("FAILED");

    private final String status;

    TransactionStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
