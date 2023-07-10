package com.murad.operationsservice.configuration;

import com.murad.operationsservice.dto.ProductBasket;
import com.murad.operationsservice.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("cart")
public interface CartServiceClient {
    @PostMapping("/get")
    ProductBasket getCart(@RequestBody UserResponse user );
    @PostMapping("/delete/by/userid")
    ProductBasket deleteAllFromCart(@RequestBody UserResponse user );
}
