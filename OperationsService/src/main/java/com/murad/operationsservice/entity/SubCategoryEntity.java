package com.murad.operationsservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "sub_category")
public class SubCategoryEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false,unique = true)
    private String name;


    @ManyToOne
    private CategoryEntity category;
    private LocalDateTime createDateTime=LocalDateTime.now();
    @OneToMany
    Set<ProductEntity> products;
}
