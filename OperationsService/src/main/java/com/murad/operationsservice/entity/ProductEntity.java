package com.murad.operationsservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue
    private Long                    id;
    private BigDecimal              price;
    private String                  name;
    @ManyToOne
    private SubCategoryEntity       subCategoryEntity;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productEntity")
    private Set<ProductImageEntity> images;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productEntity")
    private List<ProductFileEntity> file;
    private String                  Description;
    private LocalDateTime           createDateTime = LocalDateTime.now();


}
