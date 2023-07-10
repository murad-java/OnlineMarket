package com.murad.operationsservice.dto;

import com.murad.operationsservice.entity.ProductFileEntity;
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
public class BuyProductRequest {
    private String name;
    private List<ProductFileRequest> productFileEntity;
    private BigDecimal price;
    private String buyDateTime;
    private byte[] image;

}
