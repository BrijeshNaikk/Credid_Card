package com.ofss.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ofss.dto.ReportModels.AveragePurchaseReport;
import com.ofss.dto.ReportModels.CardUsageReport;
import com.ofss.dto.ReportModels.CreditCardView;
import com.ofss.dto.ReportModels.CustomerAmountReport;
import com.ofss.dto.ReportModels.CustomerOutstandingReport;
import com.ofss.dto.ReportModels.CustomerView;
import com.ofss.dto.ReportModels.DailyTotalReport;
import com.ofss.dto.ReportModels.MerchantSalesReport;
import com.ofss.dto.ReportModels.MerchantTransactionCountReport;
import com.ofss.dto.ReportModels.MerchantView;
import com.ofss.dto.ReportModels.MonthlySpendingReport;
import com.ofss.dto.ReportModels.TotalOutstandingReport;
import com.ofss.dto.ReportModels.TransactionView;
import com.ofss.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerView>> getAllCustomers(
            @RequestHeader(HttpHeaders.AUTHORIZATION)
            String authorizationHeader
    ) {
        return ResponseEntity.ok(
                reportService.getAllCustomers(authorizationHeader)
        );
    }

    @GetMapping("/credit-cards")
    public ResponseEntity<List<CreditCardView>> getAllCreditCards(
            @RequestHeader(HttpHeaders.AUTHORIZATION)
            String authorizationHeader
    ) {
        return ResponseEntity.ok(
                reportService.getAllCreditCards(authorizationHeader)
        );
    }

    @GetMapping("/merchants")
    public ResponseEntity<List<MerchantView>> getAllMerchants(
            @RequestHeader(HttpHeaders.AUTHORIZATION)
            String authorizationHeader
    ) {
        return ResponseEntity.ok(
                reportService.getAllMerchants(authorizationHeader)
        );
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionView>> getTransactionHistory(
            @RequestHeader(HttpHeaders.AUTHORIZATION)
            String authorizationHeader
    ) {
        return ResponseEntity.ok(
                reportService.getTransactionHistory(authorizationHeader)
        );
    }

    @GetMapping("/customers/highest-outstanding")
    public ResponseEntity<List<CustomerOutstandingReport>>
            getHighestOutstandingCustomers(
                    @RequestHeader(HttpHeaders.AUTHORIZATION)
                    String authorizationHeader
            ) {
        return ResponseEntity.ok(
                reportService.getHighestOutstandingCustomers(
                        authorizationHeader
                )
        );
    }

    @GetMapping("/customers/lowest-outstanding")
    public ResponseEntity<List<CustomerOutstandingReport>>
            getLowestOutstandingCustomers(
                    @RequestHeader(HttpHeaders.AUTHORIZATION)
                    String authorizationHeader
            ) {
        return ResponseEntity.ok(
                reportService.getLowestOutstandingCustomers(
                        authorizationHeader
                )
        );
    }

    @GetMapping("/merchants/highest-sales")
    public ResponseEntity<List<MerchantSalesReport>>
            getMerchantWithHighestSales(
                    @RequestHeader(HttpHeaders.AUTHORIZATION)
                    String authorizationHeader
            ) {
        return ResponseEntity.ok(
                reportService.getMerchantWithHighestSales(
                        authorizationHeader
                )
        );
    }

    @GetMapping("/merchants/highest-transaction-count")
    public ResponseEntity<List<MerchantTransactionCountReport>>
            getMerchantWithHighestTransactionCount(
                    @RequestHeader(HttpHeaders.AUTHORIZATION)
                    String authorizationHeader
            ) {
        return ResponseEntity.ok(
                reportService.getMerchantWithHighestTransactionCount(
                        authorizationHeader
                )
        );
    }

    @GetMapping("/credit-cards/most-used")
    public ResponseEntity<List<CardUsageReport>> getMostUsedCards(
            @RequestHeader(HttpHeaders.AUTHORIZATION)
            String authorizationHeader
    ) {
        return ResponseEntity.ok(
                reportService.getMostUsedCards(authorizationHeader)
        );
    }

    @GetMapping("/credit-cards/least-used")
    public ResponseEntity<List<CardUsageReport>> getLeastUsedCards(
            @RequestHeader(HttpHeaders.AUTHORIZATION)
            String authorizationHeader
    ) {
        return ResponseEntity.ok(
                reportService.getLeastUsedCards(authorizationHeader)
        );
    }

    @GetMapping("/purchases/today/total")
    public ResponseEntity<DailyTotalReport> getTodayPurchaseTotal(
            @RequestHeader(HttpHeaders.AUTHORIZATION)
            String authorizationHeader
    ) {
        return ResponseEntity.ok(
                reportService.getTodayPurchaseTotal(authorizationHeader)
        );
    }

    @GetMapping("/payments/today/total")
    public ResponseEntity<DailyTotalReport> getTodayPaymentTotal(
            @RequestHeader(HttpHeaders.AUTHORIZATION)
            String authorizationHeader
    ) {
        return ResponseEntity.ok(
                reportService.getTodayPaymentTotal(authorizationHeader)
        );
    }

    @GetMapping("/credit-cards/blocked")
    public ResponseEntity<List<CreditCardView>> getBlockedCards(
            @RequestHeader(HttpHeaders.AUTHORIZATION)
            String authorizationHeader
    ) {
        return ResponseEntity.ok(
                reportService.getBlockedCards(authorizationHeader)
        );
    }

    @GetMapping("/credit-cards/low-available-credit")
    public ResponseEntity<List<CreditCardView>>
            getCardsBelowTwentyPercent(
                    @RequestHeader(HttpHeaders.AUTHORIZATION)
                    String authorizationHeader
            ) {
        return ResponseEntity.ok(
                reportService.getCardsBelowTwentyPercent(
                        authorizationHeader
                )
        );
    }

    @GetMapping("/customers/highest-spending")
    public ResponseEntity<List<CustomerAmountReport>>
            getHighestSpendingCustomers(
                    @RequestHeader(HttpHeaders.AUTHORIZATION)
                    String authorizationHeader
            ) {
        return ResponseEntity.ok(
                reportService.getHighestSpendingCustomers(
                        authorizationHeader
                )
        );
    }

    @GetMapping("/customers/highest-payment")
    public ResponseEntity<List<CustomerAmountReport>>
            getHighestPaymentCustomers(
                    @RequestHeader(HttpHeaders.AUTHORIZATION)
                    String authorizationHeader
            ) {
        return ResponseEntity.ok(
                reportService.getHighestPaymentCustomers(
                        authorizationHeader
                )
        );
    }

    @GetMapping("/outstanding/total")
    public ResponseEntity<TotalOutstandingReport>
            getTotalOutstandingAmount(
                    @RequestHeader(HttpHeaders.AUTHORIZATION)
                    String authorizationHeader
            ) {
        return ResponseEntity.ok(
                reportService.getTotalOutstandingAmount(
                        authorizationHeader
                )
        );
    }

    @GetMapping("/purchases/average")
    public ResponseEntity<AveragePurchaseReport>
            getAveragePurchaseAmount(
                    @RequestHeader(HttpHeaders.AUTHORIZATION)
                    String authorizationHeader
            ) {
        return ResponseEntity.ok(
                reportService.getAveragePurchaseAmount(
                        authorizationHeader
                )
        );
    }

    @GetMapping("/purchases/largest")
    public ResponseEntity<List<TransactionView>> getLargestPurchase(
            @RequestHeader(HttpHeaders.AUTHORIZATION)
            String authorizationHeader
    ) {
        return ResponseEntity.ok(
                reportService.getLargestPurchase(authorizationHeader)
        );
    }

    @GetMapping("/customers/monthly-spending")
    public ResponseEntity<List<MonthlySpendingReport>>
            getMonthlySpendingSummary(
                    @RequestHeader(HttpHeaders.AUTHORIZATION)
                    String authorizationHeader
            ) {
        return ResponseEntity.ok(
                reportService.getMonthlySpendingSummary(
                        authorizationHeader
                )
        );
    }
}