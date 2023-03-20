package com.murad.operationsservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue
    private Long Id;
    private BigDecimal price;
    private String name;
    @ManyToOne
    private SubCategoryEntity subCategoryEntity;
    @OneToMany(mappedBy = "productEntity")
    private Set<ProductImageEntity> images;
    private String Description;
    private LocalDateTime createDateTime = LocalDateTime.now();

}
