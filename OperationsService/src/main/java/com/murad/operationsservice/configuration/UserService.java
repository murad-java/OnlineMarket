package com.murad.operationsservice.configuration;


import com.murad.operationsservice.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("USER")
public interface UserService {
    @GetMapping("/get/{name}")
    UserResponse getUser(@PathVariable String name);
}