package com.ofss.controller;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ofss.dto.MerchantDto;
import com.ofss.service.MerchantService;

import java.util.List;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    // POST /api/merchants
    @PostMapping
    public ResponseEntity<MerchantDto> createMerchant(
            @Valid @RequestBody MerchantDto request
    ) {
        MerchantDto merchant =
                merchantService.createMerchant(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(merchant);
    }

    // GET /api/merchants/{merchantId}
    @GetMapping("/{merchantId}")
    public ResponseEntity<MerchantDto> getMerchantById(
            @PathVariable Long merchantId
    ) {
        MerchantDto merchant =
                merchantService.getMerchantById(merchantId);

        return ResponseEntity.ok(merchant);
    }

    // GET /api/merchants
    @GetMapping
    public ResponseEntity<List<MerchantDto>> getAllMerchants() {
        List<MerchantDto> merchants =
                merchantService.getAllMerchants();

        return ResponseEntity.ok(merchants);
    }

    // PUT /api/merchants/{merchantId}
    @PutMapping("/{merchantId}")
    public ResponseEntity<MerchantDto> updateMerchant(
            @Valid @RequestBody MerchantDto request,
            @PathVariable Long merchantId
    ) {
        MerchantDto merchant =
                merchantService.updateMerchant(request, merchantId);

        return ResponseEntity.ok(merchant);
    }

    // PATCH /api/merchants/{merchantId}
    @PatchMapping("/{merchantId}")
    public ResponseEntity<MerchantDto> patchMerchant(
            @RequestBody MerchantDto request,
            @PathVariable Long merchantId
    ) {
        MerchantDto merchant =
                merchantService.patchMerchant(request, merchantId);

        return ResponseEntity.ok(merchant);
    }

    // DELETE /api/merchants/{merchantId}
    @DeleteMapping("/{merchantId}")
    public ResponseEntity<Void> deleteMerchant(
            @PathVariable Long merchantId
    ) {
        merchantService.deleteMerchant(merchantId);

        return ResponseEntity.noContent().build();
    }
}