package com.example.ecommerce.controller;

import com.example.ecommerce.dto.PurchaseDto;
import com.example.ecommerce.dto.PurchaseResponseDto;
import com.example.ecommerce.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    @PostMapping("/purchase")
    public PurchaseResponseDto placeOrder(@RequestBody PurchaseDto purchaseDto) {
        return checkoutService.placeOrder(purchaseDto);
    }
}
