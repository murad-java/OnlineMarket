package com.murad.operationsservice.repository;

import com.murad.operationsservice.entity.ProductEntity;
import com.murad.operationsservice.entity.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    List<ProductEntity> findBySubCategoryEntity(SubCategoryEntity subCategoryEntity);
    List<ProductEntity> findBySubCategoryEntityIn(Set<SubCategoryEntity> subCategoryEntities);
}
