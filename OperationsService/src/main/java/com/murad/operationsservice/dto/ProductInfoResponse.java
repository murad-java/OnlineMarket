package com.murad.operationsservice.dto;

import com.murad.operationsservice.entity.ProductEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductInfoResponse {
    private long subCatalogId;
    private long id;
    private String name;
    private String description;
    private BigDecimal price;
    private List<byte[]> img;

    public static ProductInfoResponse fromProductEntity(ProductEntity productEntity){
        ProductInfoResponse productResponse = new ProductInfoResponse();
        productResponse.setId(productEntity.getId());
        productResponse.setSubCatalogId(productEntity.getSubCategoryEntity().getId());
        productResponse.setName(productEntity.getName());
        productResponse.setDescription(productEntity.getDescription());
        productResponse.setPrice(productEntity.getPrice());
        return productResponse;
    }
}
