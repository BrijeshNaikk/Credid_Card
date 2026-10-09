package com.ofss.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import com.ofss.exception.BadRequestException;

@Component
public class MerchantServiceClient {

    private final RestClient restClient;

    public MerchantServiceClient(
            @Qualifier("loadBalancedRestClientBuilder")
            RestClient.Builder restClientBuilder
    ) {
        this.restClient = restClientBuilder.build();
    }

    public void validateMerchant(
            Long merchantId,
            String authorizationHeader
    ) {
        try {
            restClient.get()
                    .uri(
                            "http://merchant/api/merchants/{merchantId}",
                            merchantId
                    )
                    .header(HttpHeaders.AUTHORIZATION, authorizationHeader)
                    .retrieve()
                    .toBodilessEntity();

        } catch (HttpClientErrorException.NotFound exception) {
            throw new BadRequestException(
                    "Merchant with ID " + merchantId + " does not exist"
            );
        }
    }
}