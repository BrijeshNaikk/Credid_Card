package com.ofss.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public final class ReportModels {

    private ReportModels() {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record CustomerView(
            Long customerId,
            Long userId,
            String customerName,
            String email,
            String mobileNumber,
            String panNumber
    ) {
        public Long id() {
            return userId != null ? userId : customerId;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record CreditCardView(
            String cardNumber,
            Long customerId,
            String cardType,
            BigDecimal creditLimit,
            BigDecimal availableCredit,
            BigDecimal outstandingAmount,
            String expiryDate,
            String cardStatus
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record MerchantView(
            Long merchantId,
            String merchantName,
            String category,
            String location
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record TransactionView(
            Long transactionId,
            String cardNumber,
            String transactionType,
            BigDecimal amount,
            Long merchantId,
            LocalDateTime transactionDateTime,
            String transactionStatus,
            Long customerId
    ) {
    }

    public record CustomerOutstandingReport(
            Long customerId,
            String customerName,
            BigDecimal outstandingAmount
    ) {
    }

    public record MerchantSalesReport(
            Long merchantId,
            String merchantName,
            BigDecimal totalSalesAmount
    ) {
    }

    public record MerchantTransactionCountReport(
            Long merchantId,
            String merchantName,
            Long transactionCount
    ) {
    }

    public record CardUsageReport(
            String cardNumber,
            Long customerId,
            Long transactionCount
    ) {
    }

    public record DailyTotalReport(
            String transactionType,
            LocalDate date,
            BigDecimal totalAmount
    ) {
    }

    public record CustomerAmountReport(
            Long customerId,
            String customerName,
            BigDecimal totalAmount
    ) {
    }

    public record TotalOutstandingReport(
            BigDecimal totalOutstandingAmount
    ) {
    }

    public record AveragePurchaseReport(
            BigDecimal averagePurchaseAmount,
            Long purchaseTransactionCount
    ) {
    }

    public record MonthlySpendingReport(
            String month,
            Long customerId,
            String customerName,
            BigDecimal totalPurchaseAmount
    ) {
    }
}