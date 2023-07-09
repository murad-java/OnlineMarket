package com.murad.operationsservice.controller;

import com.murad.operationsservice.dto.PaymentReturnDto;
import com.murad.operationsservice.model.HashIdGeneration;
import com.murad.operationsservice.service.BuyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buy")
@RequiredArgsConstructor
public class BuyProduct {
    private final BuyService buyService;
    private final HashIdGeneration generation;

    @PostMapping("/fromCart")
    public String buyFromCart(){
        return buyService.buyFromCart();
    }
    @PostMapping("/return")
    public void returnPay(@RequestBody PaymentReturnDto dto){
        if (generation.checkHash(dto)) {
            buyService.paymentReturn(dto);
        }
    }
}
