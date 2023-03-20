package com.murad.operationsservice.repository;

import com.murad.operationsservice.entity.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity,Long> {
      Set<SubCategoryEntity> findByCategory_Id(Long id);
}
