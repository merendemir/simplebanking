package com.eteration.simplebanking.dto;

import com.eteration.simplebanking.enumeration.TransactionStatus;

import java.util.Objects;
import java.util.UUID;

/**
 * Project: simplebanking
 * Package: com.eteration.simplebanking.dto
 *
 * @author eren
 * Created at: 12.10.2024 14:46
 */
public class TransactionResponseDto {
    private TransactionStatus status;
    private UUID approvalCode;


    public TransactionResponseDto(TransactionStatus status, UUID approvalCode) {
        this.status = status;
        this.approvalCode = approvalCode;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public UUID getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(UUID approvalCode) {
        this.approvalCode = approvalCode;
    }

    public static TransactionResponseDto success(UUID approvalCode) {
        return new TransactionResponseDto(TransactionStatus.OK, approvalCode);
    }

    public static TransactionResponseDto failed() {
        return new TransactionResponseDto(TransactionStatus.FAILED, null);
    }

    @Override
    public String toString() {
        return "TransactionResponseDto{" +
                "status=" + status +
                ", approvalCode=" + approvalCode +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionResponseDto that = (TransactionResponseDto) o;
        return status == that.status && Objects.equals(approvalCode, that.approvalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, approvalCode);
    }
}
