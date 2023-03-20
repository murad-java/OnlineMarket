package com.murad.operationsservice.dto;

import com.murad.operationsservice.entity.SubCategoryEntity;
import lombok.Data;

@Data
public class SubCategoryResponse {
    private Long id;
    private Long categoryId;
    private String name;

    public static SubCategoryResponse fromSubCategoryEntity(SubCategoryEntity subCategoryEntity){
        SubCategoryResponse subCategoryResponse =new SubCategoryResponse();
        subCategoryResponse.setId(subCategoryEntity.getId());
        subCategoryResponse.setCategoryId(subCategoryEntity.getCategory().getId());
        subCategoryResponse.setName(subCategoryEntity.getName());
        return subCategoryResponse;
    }
}
