package com.ofss.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.ofss.dto.CreditCardStatementDto;
import com.ofss.dto.TransactionDto;

public interface TransactionService {

	TransactionDto createTransaction(
	        TransactionDto request,
	        String authorizationHeader
	);

    TransactionDto getTransactionById(Long transactionId);

    List<TransactionDto> getAllTransactions();

    TransactionDto updateTransaction(Long transactionId, TransactionDto request);

    TransactionDto patchTransaction(Long transactionId, TransactionDto request);

    void deleteTransaction(Long transactionId);
    
    List<TransactionDto> getTransactionsForLoggedInUser(Long userId);

    TransactionDto getTransactionForLoggedInUser(
            Long transactionId,
            Long userId
    );
    
    List<TransactionDto> searchTransactionsByDateRange(
            LocalDate fromDate,
            LocalDate toDate
    );

    List<TransactionDto> searchTransactionsByMerchant(
            Long merchantId
    );

    List<TransactionDto> searchTransactionsByAmount(
            BigDecimal minimumAmount,
            BigDecimal maximumAmount
    );

    CreditCardStatementDto generateStatement(
            String cardNumber,
            LocalDate fromDate,
            LocalDate toDate,
            Long customerId
    );
}