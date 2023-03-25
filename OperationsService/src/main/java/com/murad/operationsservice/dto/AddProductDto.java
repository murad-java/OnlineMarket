package com.murad.operationsservice.dto;

import com.murad.operationsservice.entity.ProductEntity;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AddProductDto {
    private long subCategoryId;
    private String name;
    private String description;
    private BigDecimal price;
    private List<MultipartFile> files;

}
