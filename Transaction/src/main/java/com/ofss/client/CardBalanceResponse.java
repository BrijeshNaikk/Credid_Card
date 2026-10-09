package com.ofss.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CardBalanceResponse(

        Long customerId
) {
}