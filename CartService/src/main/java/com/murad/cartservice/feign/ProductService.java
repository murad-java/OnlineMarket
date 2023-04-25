package com.murad.cartservice.feign;

import com.murad.cartservice.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "OPERATION")
public interface ProductService {
    @PostMapping("/product/get")
    List<ProductResponse> getProductsByIds(@RequestBody List<Long> ids);
}
