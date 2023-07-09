package com.murad.operationsservice.configuration;

import com.murad.operationsservice.dto.ProductBasket;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("cart")
public interface CartServiceClient {
    @GetMapping("/get")
    ProductBasket getCart();
}
