package com.murad.operationsservice.controller;

import com.murad.operationsservice.dto.BuyProductDto;
import com.murad.operationsservice.dto.BuyProductRequest;
import com.murad.operationsservice.dto.PaymentRequest;
import com.murad.operationsservice.dto.PaymentReturnDto;
import com.murad.operationsservice.model.HashIdGeneration;
import com.murad.operationsservice.service.BuyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/buy")
@RequiredArgsConstructor
public class BuyProduct {
    private final BuyService       buyService;
    private final HashIdGeneration generation;

    @PostMapping("/get/products")
    public List<BuyProductRequest> getBuyProducts() {
        return buyService.getBuyProducts();
    }

    @PostMapping("/fromCart")
    public PaymentRequest buyFromCart() {
        return buyService.buyFromCart();
    }

    @PostMapping("/product")
    public PaymentRequest buyProduct(@RequestBody BuyProductDto buyProductDto) {
        return buyService.buyProduct(buyProductDto);
    }

    @PostMapping("/return")
    public void returnPay(@RequestBody PaymentReturnDto dto) {
        if (generation.checkHash(dto)) {
            buyService.paymentReturn(dto);
        }
    }
}
