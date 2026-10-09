package com.ofss.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.ofss.dto.ReportModels.CreditCardView;
import com.ofss.dto.ReportModels.CustomerView;
import com.ofss.dto.ReportModels.MerchantView;
import com.ofss.dto.ReportModels.TransactionView;

@Component
public class ReportingDataClient {

    private final RestClient restClient;

    public ReportingDataClient(
            @Qualifier("loadBalancedRestClientBuilder")
            RestClient.Builder restClientBuilder
    ) {
        this.restClient = restClientBuilder.build();
    }

    public List<CustomerView> getCustomers(String authorizationHeader) {

        List<CustomerView> customers = restClient.get()
                .uri("http://customer/api/customers")
                .header(HttpHeaders.AUTHORIZATION, authorizationHeader)
                .retrieve()
                .body(new ParameterizedTypeReference<List<CustomerView>>() {
                });

        return customers == null ? List.of() : customers;
    }

    public List<CreditCardView> getCreditCards(String authorizationHeader) {

        List<CreditCardView> cards = restClient.get()
                .uri("http://creditcard/api/credit-cards")
                .header(HttpHeaders.AUTHORIZATION, authorizationHeader)
                .retrieve()
                .body(new ParameterizedTypeReference<List<CreditCardView>>() {
                });

        return cards == null ? List.of() : cards;
    }

    public List<MerchantView> getMerchants(String authorizationHeader) {

        List<MerchantView> merchants = restClient.get()
                .uri("http://merchant/api/merchants")
                .header(HttpHeaders.AUTHORIZATION, authorizationHeader)
                .retrieve()
                .body(new ParameterizedTypeReference<List<MerchantView>>() {
                });

        return merchants == null ? List.of() : merchants;
    }

    public List<TransactionView> getTransactions(String authorizationHeader) {

        List<TransactionView> transactions = restClient.get()
                .uri("http://transaction/api/transactions")
                .header(HttpHeaders.AUTHORIZATION, authorizationHeader)
                .retrieve()
                .body(new ParameterizedTypeReference<List<TransactionView>>() {
                });

        return transactions == null ? List.of() : transactions;
    }
}