package com.eteration.simplebanking.dto;

import java.util.Objects;

/**
 * Project: simplebanking
 * Package: com.eteration.simplebanking.dto
 *
 * @author eren
 * Created at: 12.10.2024 14:46
 */
public class TransactionRequestDto {
    private double amount;

    public TransactionRequestDto() {
    }

    public TransactionRequestDto(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "TransactionRequestDto{" +
                "amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionRequestDto that = (TransactionRequestDto) o;
        return Double.compare(amount, that.amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
