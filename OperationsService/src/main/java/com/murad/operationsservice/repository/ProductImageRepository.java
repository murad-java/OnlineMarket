package com.murad.operationsservice.repository;

import com.murad.operationsservice.entity.ProductEntity;
import com.murad.operationsservice.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImageEntity,Long> {
    List<ProductImageEntity> findByProductEntity(ProductEntity productEntity);
    ProductImageEntity findFirstByProductEntity(ProductEntity productEntity);
}
