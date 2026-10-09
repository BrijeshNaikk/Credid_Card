package com.ofss.serviceImpl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ofss.dto.MerchantDto;
import com.ofss.entity.Merchant;
import com.ofss.exception.BadRequestException;
import com.ofss.exception.ResourceNotFoundException;
import com.ofss.repository.MerchantRepository;
import com.ofss.service.MerchantService;

import java.util.List;

@Service
@Transactional
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;

    public MerchantServiceImpl(
            MerchantRepository merchantRepository
    ) {
        this.merchantRepository = merchantRepository;
    }

    // POST: Create a merchant
    @Override
    public MerchantDto createMerchant(MerchantDto request) {

        Merchant merchant = new Merchant();

        merchant.setMerchantName(request.merchantName());
        merchant.setCategory(request.category());
        merchant.setLocation(request.location());

        Merchant savedMerchant = merchantRepository.save(merchant);

        return toDto(savedMerchant);
    }

    // GET: Retrieve one merchant
    @Override
    @Transactional(readOnly = true)
    public MerchantDto getMerchantById(Long merchantId) {

        Merchant merchant = findMerchantById(merchantId);

        return toDto(merchant);
    }

    // GET: Retrieve all merchants
    @Override
    @Transactional(readOnly = true)
    public List<MerchantDto> getAllMerchants() {

        return merchantRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    // PUT: Replace all merchant details
    @Override
    public MerchantDto updateMerchant(
            MerchantDto request,
            Long merchantId
    ) {

        Merchant merchant = findMerchantById(merchantId);

        merchant.setMerchantName(request.merchantName());
        merchant.setCategory(request.category());
        merchant.setLocation(request.location());

        Merchant updatedMerchant = merchantRepository.save(merchant);

        return toDto(updatedMerchant);
    }

    // PATCH: Update only supplied non-null fields
    @Override
    public MerchantDto patchMerchant(
            MerchantDto request,
            Long merchantId
    ) {

        if (request == null) {
            throw new BadRequestException("Request body is required");
        }

        Merchant merchant = findMerchantById(merchantId);

        if (request.merchantName() != null) {
            merchant.setMerchantName(request.merchantName());
        }

        if (request.category() != null) {
            merchant.setCategory(request.category());
        }

        if (request.location() != null) {
            merchant.setLocation(request.location());
        }

        Merchant updatedMerchant = merchantRepository.save(merchant);

        return toDto(updatedMerchant);
    }

    // DELETE: Delete a merchant
    @Override
    public void deleteMerchant(Long merchantId) {

        Merchant merchant = findMerchantById(merchantId);

        merchantRepository.delete(merchant);
    }

    private Merchant findMerchantById(Long merchantId) {

        if (merchantId == null || merchantId <= 0) {
            throw new BadRequestException(
                    "Merchant ID must be a positive number"
            );
        }

        return merchantRepository.findById(merchantId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Merchant",
                                merchantId.toString()
                        )
                );
    }

    private MerchantDto toDto(Merchant merchant) {

        return new MerchantDto(
                merchant.getMerchantId(),
                merchant.getMerchantName(),
                merchant.getCategory(),
                merchant.getLocation()
        );
    }
}