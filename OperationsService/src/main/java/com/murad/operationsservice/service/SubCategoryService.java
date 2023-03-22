package com.murad.operationsservice.service;

import com.murad.operationsservice.dto.SubCategoryDto;
import com.murad.operationsservice.dto.SubCategoryResponse;
import com.murad.operationsservice.dto.SubCategoryUpdateDto;
import com.murad.operationsservice.entity.CategoryEntity;
import com.murad.operationsservice.entity.SubCategoryEntity;
import com.murad.operationsservice.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;
    private final CategoryService categoryService;

    public SubCategoryResponse getSubCategoryByIdForResponse(long subCategoryId) {
        SubCategoryEntity subCategoryEntity = getSubCategoryById(subCategoryId);
        return SubCategoryResponse.fromSubCategoryEntity(subCategoryEntity);
    }

    public SubCategoryEntity getSubCategoryById(long subCategoryId) {
        SubCategoryEntity subCategoryEntity = subCategoryRepository.findById(subCategoryId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Subcategory not found with subCategoryId " + subCategoryId));
        return subCategoryEntity;
    }

    public Set<SubCategoryResponse> getSubCategoryByCategoryId(long categoryId) {
        Set<SubCategoryEntity> subCategoryEntities = subCategoryRepository.findByCategory_Id(categoryId);
        Set<SubCategoryResponse> subCategoryResponses = subCategoryEntities.stream().map(SubCategoryResponse::fromSubCategoryEntity).collect(Collectors.toSet());
        return subCategoryResponses;
    }

    public void createSubCategory(SubCategoryDto dto) {
        CategoryEntity categoryEntity = categoryService.getCategoryById(dto.getCategoryId());
        SubCategoryEntity subCategoryEntity = new SubCategoryEntity();
        subCategoryEntity.setCategory(categoryEntity);
        subCategoryEntity.setName(dto.getName());
        subCategoryRepository.save(subCategoryEntity);
    }

    public void updateSubCategory(SubCategoryUpdateDto dto) {
        SubCategoryEntity subCategoryEntity =getSubCategoryById(dto.getId());
        subCategoryEntity.setName(dto.getName());
        subCategoryRepository.save(subCategoryEntity);
    }
    public void deleteSubCategory(long id){
        subCategoryRepository.deleteById(id);
    }
}
