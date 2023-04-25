package com.murad.cartservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductBasket {
    List<CartResponse> products;
    BigDecimal totalPrice;

    public static ProductBasket fromProductResponsesAndCartResponses(List<ProductResponse> productResponses, List<CartResponse> cartResponses) {
        BigDecimal total=new BigDecimal(0);
        for (var cart : cartResponses){
            for (var product: productResponses){
                if(cart.getProductId()==product.getId()){
                    cart.setName(product.getName());
                    cart.setPrice(product.getPrice());
                }
            }
           total= total.add(cart.getPrice().multiply(new BigDecimal(cart.getCount())));
        }

        ProductBasket productBasket = ProductBasket.builder().products(cartResponses).totalPrice(total).build();

        return productBasket;
    }
}
