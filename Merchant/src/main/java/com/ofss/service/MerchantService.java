package com.ofss.service;

import java.util.List;

import com.ofss.dto.MerchantDto;

public interface MerchantService {

    MerchantDto createMerchant(MerchantDto request);

    MerchantDto getMerchantById(Long merchantId);

    List<MerchantDto> getAllMerchants();

    MerchantDto updateMerchant(
            MerchantDto request,
            Long merchantId
    );

    MerchantDto patchMerchant(
            MerchantDto request,
            Long merchantId
    );

    void deleteMerchant(Long merchantId);
}
