package com.murad.operationsservice.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product_image")
@Data
public class ProductImageEntity {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;
    private String url;

}