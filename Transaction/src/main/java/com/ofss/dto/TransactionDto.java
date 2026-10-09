package com.ofss.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ofss.entity.TransactionStatus;
import com.ofss.entity.TransactionType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record TransactionDto(

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long transactionId,

        @NotBlank(message = "Card number is required")
        @Size(max = 19, message = "Card number must not exceed 19 characters")
        String cardNumber,

        @NotNull(message = "Transaction type is required")
        TransactionType transactionType,

        @NotNull(message = "Amount is required")
        @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
        @Digits(integer = 13, fraction = 2,
                message = "Amount can contain up to 13 digits and 2 decimal places")
        BigDecimal amount,

        Long merchantId,

        @NotNull(message = "Transaction date and time is required")
        LocalDateTime transactionDateTime,

        @NotNull(message = "Transaction status is required")
        TransactionStatus transactionStatus,
        
        @NotNull(message = "Customer ID is required")
        @Positive(message = "Customer ID must be greater than zero")
        Long customerId
) {
}

