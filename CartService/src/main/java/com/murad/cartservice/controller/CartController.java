package com.murad.cartservice.controller;

import com.murad.cartservice.dto.CountOperation;
import com.murad.cartservice.dto.ProductBasket;
import com.murad.cartservice.dto.SetCount;
import com.murad.cartservice.dto.UserResponse;
import com.murad.cartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
//@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000", "http://192.168.1.123:8080"})
public class CartController {
    private final CartService cartService;

    @GetMapping("/get")
    public ProductBasket getProductInCartByUserId() {
        return cartService.getCart();
    }
    @PostMapping("/get")
    public ProductBasket getProductInCartByUser(@RequestBody UserResponse user ) {
        return cartService.getCart(user);
    }
    @GetMapping("/count")
    public int getCount(){
        return cartService.getCount();
    }

    @PostMapping("/up")
    public ProductBasket upCount(@RequestBody CountOperation countOperation) {

        return cartService.upCount(countOperation.getId());
    }

    @PostMapping("/down")
    public ProductBasket downCount(@RequestBody CountOperation countOperation) {
        return cartService.downCount(countOperation.getId());
    }

    @PostMapping("/set")
    public ProductBasket setCount(@RequestBody SetCount setCount) {
        return cartService.setCount(setCount);
    }

    @PostMapping("/add")
    public ProductBasket addProductInCart(@RequestBody CountOperation countOperation) {
        return cartService.addProductInCart(countOperation.getId());
    }

    @PostMapping("/delete")
    public ResponseEntity<ProductBasket> deleteProductFromCart(@RequestBody CountOperation countOperation) {
        return ResponseEntity.ok(cartService.deleteProductFromCart(countOperation.getId()));
    }
    @PostMapping("/delete/by/userid")
    public void deleteByUserId(@RequestBody UserResponse userResponse){
        cartService.deleteAllByUserId(userResponse);
    }
}
