package com.murad.operationsservice.service;

import com.murad.operationsservice.entity.ProductEntity;
import com.murad.operationsservice.entity.ProductImageEntity;
import com.murad.operationsservice.entity.SubCategoryEntity;
import com.murad.operationsservice.exception.ResourceNotFoundException;
import com.murad.operationsservice.repository.ProductImageRepository;
import com.murad.operationsservice.repository.ProductRepository;
import com.murad.operationsservice.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final SubCategoryService subCategoryService;

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public List<ProductEntity> getAllProductsBySubCategory(long id) {
        SubCategoryEntity subCategoryEntity = subCategoryService.getSubCategoryById(id);
        return productRepository.findBySubCategoryEntity(subCategoryEntity);
    }

    public ProductEntity getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product with id " + id + " not found."));
    }

    public ProductEntity saveProduct(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    public void deleteProductById(Long id) {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        if (productEntityOptional.isPresent()) {
            ProductEntity productEntity = productEntityOptional.get();
            productImageRepository.deleteAll(productEntity.getImages());
            productRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Product with id " + id + " not found.");
        }
    }

    public List<ProductImageEntity> getAllProductImages() {
        return productImageRepository.findAll();
    }

    public ProductImageEntity getProductImageById(Long id) {
        return productImageRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product with id " + id + " not found."));
    }

    public ProductImageEntity saveProductImage(ProductImageEntity productImageEntity) {
        return productImageRepository.save(productImageEntity);
    }

    public void deleteProductImageById(Long id) {
            productImageRepository.deleteById(id);
    }
}
