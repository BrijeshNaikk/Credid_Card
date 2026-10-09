package com.ofss.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record CreditCardStatementDto(

        String cardNumber,

        Long customerId,

        LocalDate fromDate,

        LocalDate toDate,

        BigDecimal totalPurchaseAmount,

        BigDecimal totalPaymentAmount,

        BigDecimal netAmountForPeriod,

        List<TransactionDto> transactions
) {
}