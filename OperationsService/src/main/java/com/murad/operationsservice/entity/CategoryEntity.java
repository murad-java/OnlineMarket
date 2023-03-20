package com.murad.operationsservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "category", indexes = {
        @Index(name = "idx_category_name", columnList = "name")
})
public class CategoryEntity {
    @Id
    @GeneratedValue
    Long id;
    @Column(nullable = false,unique = true)
    String name;
    @Column(nullable = false)
    LocalDateTime createDateTime = LocalDateTime.now();
    @OneToMany
    private Set<SubCategoryEntity> subCategory;
}
