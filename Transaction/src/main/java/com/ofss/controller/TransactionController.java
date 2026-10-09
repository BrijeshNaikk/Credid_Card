package com.ofss.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ofss.dto.CreditCardStatementDto;
import com.ofss.dto.TransactionDto;
import com.ofss.security.JwtUserPrincipal;
import com.ofss.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(
            @Valid @RequestBody TransactionDto request,
            @RequestHeader(HttpHeaders.AUTHORIZATION)
            String authorizationHeader
    ) {
        TransactionDto createdTransaction =
                transactionService.createTransaction(
                        request,
                        authorizationHeader
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdTransaction);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionDto> getTransactionById(
            @PathVariable Long transactionId
    ) {

        TransactionDto transaction =
                transactionService.getTransactionById(transactionId);

        return ResponseEntity.ok(transaction);
    }

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {

        List<TransactionDto> transactions =
                transactionService.getAllTransactions();

        return ResponseEntity.ok(transactions);
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<TransactionDto> updateTransaction(
            @PathVariable Long transactionId,
            @Valid @RequestBody TransactionDto request
    ) {

        TransactionDto updatedTransaction =
                transactionService.updateTransaction(transactionId, request);

        return ResponseEntity.ok(updatedTransaction);
    }

    @PatchMapping("/{transactionId}")
    public ResponseEntity<TransactionDto> patchTransaction(
            @PathVariable Long transactionId,
            @RequestBody TransactionDto request
    ) {

        TransactionDto updatedTransaction =
                transactionService.patchTransaction(transactionId, request);

        return ResponseEntity.ok(updatedTransaction);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(
            @PathVariable Long transactionId
    ) {

        transactionService.deleteTransaction(transactionId);

        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/me")
    public ResponseEntity<List<TransactionDto>> getMyTransactions(
            @AuthenticationPrincipal JwtUserPrincipal loggedInUser
    ) {

        List<TransactionDto> transactions =
                transactionService.getTransactionsForLoggedInUser(
                        loggedInUser.userId()
                );

        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/me/{transactionId}")
    public ResponseEntity<TransactionDto> getMyTransactionById(
            @PathVariable Long transactionId,
            @AuthenticationPrincipal JwtUserPrincipal loggedInUser
    ) {

        TransactionDto transaction =
                transactionService.getTransactionForLoggedInUser(
                        transactionId,
                        loggedInUser.userId()
                );

        return ResponseEntity.ok(transaction);
    }
    
    @GetMapping("/search/date-range")
    public ResponseEntity<List<TransactionDto>> searchByDateRange(
            @RequestParam LocalDate fromDate,
            @RequestParam LocalDate toDate
    ) {

        return ResponseEntity.ok(
                transactionService.searchTransactionsByDateRange(
                        fromDate,
                        toDate
                )
        );
    }

    @GetMapping("/search/merchant/{merchantId}")
    public ResponseEntity<List<TransactionDto>> searchByMerchant(
            @PathVariable Long merchantId
    ) {

        return ResponseEntity.ok(
                transactionService.searchTransactionsByMerchant(
                        merchantId
                )
        );
    }

    @GetMapping("/search/amount")
    public ResponseEntity<List<TransactionDto>> searchByAmount(
            @RequestParam BigDecimal minimumAmount,
            @RequestParam BigDecimal maximumAmount
    ) {

        return ResponseEntity.ok(
                transactionService.searchTransactionsByAmount(
                        minimumAmount,
                        maximumAmount
                )
        );
    }

    @GetMapping("/statement")
    public ResponseEntity<CreditCardStatementDto> generateStatement(
            @RequestParam String cardNumber,
            @RequestParam LocalDate fromDate,
            @RequestParam LocalDate toDate,
            @RequestParam Long customerId
    ) {

        return ResponseEntity.ok(
                transactionService.generateStatement(
                        cardNumber,
                        fromDate,
                        toDate,
                        customerId
                )
        );
    }
}