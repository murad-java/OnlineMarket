package com.murad.operationsservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.context.annotation.Description;

import javax.persistence.*;

@Entity
@Table(name = "product_file")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String fileUrl;
    @Comment("Размер файла задается в мегабайтах можно изпользовать '.' для дробных частей. Например 1024.2Мб = 1Гб 200 Мб")
    private float size;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;







}