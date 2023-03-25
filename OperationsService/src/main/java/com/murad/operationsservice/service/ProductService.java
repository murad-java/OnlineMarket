package com.murad.operationsservice.service;

import com.murad.operationsservice.dto.AddProductDto;
import com.murad.operationsservice.entity.ProductEntity;
import com.murad.operationsservice.entity.ProductImageEntity;
import com.murad.operationsservice.entity.SubCategoryEntity;
import com.murad.operationsservice.exception.ResourceNotFoundException;
import com.murad.operationsservice.repository.ProductImageRepository;
import com.murad.operationsservice.repository.ProductRepository;
import com.murad.operationsservice.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final SubCategoryService subCategoryService;

    @Value("${ftp.server.directory}")
    private String fileDirectory;

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

    public void saveProduct(AddProductDto addProductDto) {
        ProductEntity productEntity =new ProductEntity();
        productEntity.setName(addProductDto.getName());
        productEntity.setPrice(addProductDto.getPrice());
        productEntity.setDescription(addProductDto.getDescription());
        SubCategoryEntity subCategoryEntity =subCategoryService.getSubCategoryById(addProductDto.getSubCategoryId());
        productEntity.setSubCategoryEntity(subCategoryEntity);
        ProductEntity finalProductEntity  = productRepository.save(productEntity);
        Set<ProductImageEntity> productImageEntities = new HashSet<>();
        addProductDto.getFiles().forEach(multipartFile -> {
            ProductImageEntity productImageEntity = new ProductImageEntity();
            productImageEntity.setProductEntity(finalProductEntity);
            productImageEntity.setUrl(fileDirectory+"/"+multipartFile.getOriginalFilename());
            productImageEntities.add(productImageRepository.save(productImageEntity));
        });
        productRepository.save(finalProductEntity);
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
