package com.murad.operationsservice.service;

import com.murad.operationsservice.dto.AddCategoryDto;
import com.murad.operationsservice.dto.CategoriesResponse;
import com.murad.operationsservice.dto.UpdateCategoryDto;
import com.murad.operationsservice.entity.CategoryEntity;
import com.murad.operationsservice.exception.CategoryNotFoundException;
import com.murad.operationsservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Set<CategoriesResponse> getAll() {
        Set<CategoriesResponse> categoriesResponses =
        categoryRepository.findAll().stream()
                .map(CategoriesResponse::fromCategoryEntity)
                .collect(Collectors.toSet());
        return categoriesResponses;
    }

    public void addCategory(AddCategoryDto category) {
        CategoryEntity categoryEntity =new CategoryEntity();
        categoryEntity.setName(category.getCategoryName());
        categoryRepository.save(categoryEntity);
    }

    public CategoryEntity getCategoryById(Long id) throws CategoryNotFoundException {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " not found"));
    }
    public CategoryEntity getCategoryByName(String name) throws CategoryNotFoundException {
        return categoryRepository.findFirstByName(name)
                .orElseThrow(() -> new CategoryNotFoundException("Category with name " + name + " not found"));
    }
    public void updateCategory(UpdateCategoryDto category) {
        CategoryEntity categoryEntity = getCategoryByName(category.getOldName());
        categoryEntity.setName(category.getNewName());
        categoryEntity.setCreateDateTime(LocalDateTime.now());
        categoryRepository.save(categoryEntity);
    }

    public void deleteCategory(Long id) {
        CategoryEntity categoryEntity = getCategoryById(id);
        categoryRepository.delete(categoryEntity);
    }
}
