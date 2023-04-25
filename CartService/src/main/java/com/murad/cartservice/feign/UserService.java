package com.murad.cartservice.feign;

import com.murad.cartservice.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("USER")
public interface UserService {
    @GetMapping("/get/{name}")
    UserResponse getUser(@PathVariable String name);
}
