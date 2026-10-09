package com.ofss.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MerchantDto(

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long merchantId,

        @NotBlank(message = "Merchant name is required")
        @Size(max = 100, message = "Merchant name cannot exceed 100 characters")
        String merchantName,

        @NotBlank(message = "Category is required")
        @Size(max = 50, message = "Category cannot exceed 50 characters")
        String category,

        @NotBlank(message = "Location is required")
        @Size(max = 150, message = "Location cannot exceed 150 characters")
        String location
) {
}
