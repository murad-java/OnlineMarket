package com.murad.operationsservice.dto;


import com.murad.operationsservice.entity.ProductEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AllProductResponse {
    private long subCatalogId;
    private long id;
    private String name;
    private String description;
    private BigDecimal price;
    private byte[] img;

    public static AllProductResponse fromProductEntity(ProductEntity productEntity){
        AllProductResponse productResponse = new AllProductResponse();
        productResponse.setId(productEntity.getId());
        productResponse.setSubCatalogId(productEntity.getSubCategoryEntity().getId());
        productResponse.setName(productEntity.getName());
        productResponse.setDescription(productEntity.getDescription());
        productResponse.setPrice(productEntity.getPrice());
        return productResponse;
    }
}
