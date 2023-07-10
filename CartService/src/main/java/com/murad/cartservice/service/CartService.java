package com.murad.cartservice.service;

import com.murad.cartservice.dto.*;
import com.murad.cartservice.entity.CartEntity;
import com.murad.cartservice.exception.InfoNotFoundException;
import com.murad.cartservice.feign.ProductService;
import com.murad.cartservice.feign.UserService;
import com.murad.cartservice.repository.CartRepository;
import com.murad.cartservice.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final UserService userService;

    private UserResponse getUser(){
        var userName = SecurityUtils.getCurrentUsername().orElseThrow(() -> {
            throw new InfoNotFoundException("Not found name in JWT!");
        });
        return userService.getUser(userName);
    }

    private List<CartEntity> getCartEntityByUser() {
        var userResponse = getUser();
        var cartEntities = cartRepository.findByUserId(userResponse.getId());
        return cartEntities;
    }
    private List<CartEntity> getCartEntityByUser(UserResponse userResponse) {
        var cartEntities = cartRepository.findByUserId(userResponse.getId());
        return cartEntities;
    }
    private CartEntity getProductInCart(Long id){
        return cartRepository.findById(id).orElseThrow(() -> {
            throw new InfoNotFoundException("Not found product in cart!");
        });
    }

    public ProductBasket getCart() {
        var cartEntities = getCartEntityByUser();

        return getProduct(cartEntities);
    }

    public ProductBasket upCount(long id) {
        var cartEntity = getProductInCart(id);
        if (cartEntity.getCount() < 10) {
            cartEntity.setCount(cartEntity.getCount() + 1);
            cartRepository.save(cartEntity);
        }
        return getCart();
    }

    public ProductBasket downCount(long id) {
        var cartEntity = getProductInCart(id);
        if (cartEntity == null) return null;
        if (cartEntity.getCount() > 1) {
            cartEntity.setCount(cartEntity.getCount() - 1);
            cartRepository.save(cartEntity);
        }
        return getCart();
    }

    public ProductBasket setCount(SetCount setCount) {
        var cartEntity = getProductInCart(setCount.getId());
        if (cartEntity == null) return null;
        if (setCount.getCount() > 10) cartEntity.setCount(10);
        else if (setCount.getCount() < 1) cartEntity.setCount(1);
        else cartEntity.setCount(setCount.getCount());
        cartRepository.save(cartEntity);
        return getCart();
    }
    private CartEntity getProductInCartByProductId(Long id,Long userId){
        return cartRepository.findFirstByProductIdAndUserId(id,userId);
    }

    public ProductBasket addProductInCart(Long productId) {
        var userResponse = getUser();
        var cartEntity = getProductInCartByProductId(productId,userResponse.getId());
        if(cartEntity==null) {
            cartEntity = new CartEntity();
            cartEntity.setProductId(productId);
            cartEntity.setUserId(userResponse.getId());
            cartRepository.save(cartEntity);
        }else upCount(cartEntity.getId());
        return getCart();
    }
    public ProductBasket deleteProductFromCart(Long productId) {
        try {
            cartRepository.deleteById(productId);
        }catch (Exception e){
           throw   new InfoNotFoundException("Not product id"+productId+" in cart!");
        }

        return getCart();
    }
    public void deleteAllByUserId(FromCartDeleteDto user){
       List<CartEntity> cartEntities= cartRepository.findByUserId(user.getUserId());
       for(CartEntity entity:cartEntities){
           if(user.getProductIds().stream().anyMatch(aLong -> aLong== entity.getProductId())){
               cartRepository.delete(entity);
           }
       }
    }

    public int getCount() {
        ProductBasket productBasket = getCart();
        if(productBasket!=null && productBasket.getProducts()!=null) {
            return productBasket.getProducts().size();
        }else return 0;
    }

    public ProductBasket getCart(UserResponse user) {
        var cartEntities = getCartEntityByUser(user);

        return getProduct(cartEntities);
    }

    private ProductBasket getProduct(List<CartEntity> cartEntities) {
        if (cartEntities == null || cartEntities.size() == 0) return null;
        var ids = cartEntities.stream().map(cartEntity -> cartEntity.getProductId()).collect(Collectors.toList());
        var productResponseList = productService.getProductsByIds(ids);
        var cartResponses = cartEntities.stream()
                .map(cartEntity -> CartResponse.builder().id(cartEntity.getId()).count(cartEntity.getCount()).productId(cartEntity.getProductId()).build())
                .collect(Collectors.toList());
        return ProductBasket.fromProductResponsesAndCartResponses(productResponseList, cartResponses);
    }
}
