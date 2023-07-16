package com.murad.cartservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {
    private long       id;
    private long       productId;
    private String     name;
    private BigDecimal price;
    private int        count;
}
