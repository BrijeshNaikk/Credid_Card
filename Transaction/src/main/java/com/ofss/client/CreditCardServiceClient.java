package com.ofss.client;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import com.ofss.exception.BadRequestException;

@Component
public class CreditCardServiceClient {

    private final RestClient restClient;

    public CreditCardServiceClient(
            @Qualifier("loadBalancedRestClientBuilder")
            RestClient.Builder restClientBuilder
    ) {
        this.restClient = restClientBuilder.build();
    }

    public CardBalanceResponse getCard(
            String cardNumber,
            String authorizationHeader
    ) {
        try {
            return restClient.get()
                    .uri(
                            "http://creditcard/api/credit-cards/{cardNumber}",
                            cardNumber
                    )
                    .header(HttpHeaders.AUTHORIZATION, authorizationHeader)
                    .retrieve()
                    .body(CardBalanceResponse.class);

        } catch (HttpClientErrorException.NotFound exception) {
            throw new BadRequestException(
                    "Credit card " + cardNumber + " does not exist"
            );
        }
    }

    public CardBalanceResponse debitCard(
            String cardNumber,
            BigDecimal amount,
            String authorizationHeader
    ) {
        try {
            return restClient.post()
                    .uri(
                            "http://creditcard/api/credit-cards/{cardNumber}/debit",
                            cardNumber
                    )
                    .header(HttpHeaders.AUTHORIZATION, authorizationHeader)
                    .body(new CardAmountRequest(amount))
                    .retrieve()
                    .body(CardBalanceResponse.class);

        } catch (HttpClientErrorException.BadRequest exception) {
            throw new BadRequestException(
                    "Transaction declined. Check card status and available credit"
            );
        }
    }

    public CardBalanceResponse applyPayment(
            String cardNumber,
            BigDecimal amount,
            String authorizationHeader
    ) {
        try {
            return restClient.post()
                    .uri(
                            "http://creditcard/api/credit-cards/{cardNumber}/payment",
                            cardNumber
                    )
                    .header(HttpHeaders.AUTHORIZATION, authorizationHeader)
                    .body(new CardAmountRequest(amount))
                    .retrieve()
                    .body(CardBalanceResponse.class);

        } catch (HttpClientErrorException.BadRequest exception) {
            throw new BadRequestException(
                    "Payment was rejected. Check the outstanding amount"
            );
        }
    }

    private record CardAmountRequest(BigDecimal amount) {
    }
}
