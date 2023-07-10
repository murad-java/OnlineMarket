package com.murad.operationsservice.dto;

import com.murad.operationsservice.entity.ProductFileEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductFileRequest {
    private String fileUrl;
    private float size;

    public static List<ProductFileRequest> converData(List<ProductFileEntity> file) {
        return file.stream().map(product -> {
            return ProductFileRequest.builder().fileUrl(product.getFileUrl()).size(product.getSize()).build();
        }).collect(Collectors.toList());
    }
}
