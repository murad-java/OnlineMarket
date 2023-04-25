package com.murad.operationsservice.service;

import com.murad.operationsservice.dto.*;
import com.murad.operationsservice.entity.ProductEntity;
import com.murad.operationsservice.entity.ProductImageEntity;
import com.murad.operationsservice.entity.SubCategoryEntity;
import com.murad.operationsservice.exception.ResourceNotFoundException;
import com.murad.operationsservice.repository.ProductImageRepository;
import com.murad.operationsservice.repository.ProductRepository;
import com.murad.operationsservice.repository.SubCategoryRepository;
import com.netflix.discovery.provider.Serializer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final SubCategoryService subCategoryService;
    private final SubCategoryRepository subCategoryRepository;
    private final FileService fileService;
    @Value("${ftp.server.directory}")
    private String fileDirectory;

    public List<AllProductResponse> getAllProducts() {
        List<AllProductResponse> productResponseList = new ArrayList<>();
        List<ProductEntity> productEntityList = productRepository.findAll();
        productEntityList.forEach(productEntity -> {
            AllProductResponse productResponse = AllProductResponse.fromProductEntity(productEntity);
            productResponseList.add(productResponse);
            productResponse.setImg(getFirstPhotoByProduct(productEntity.getId()));
        });
        return productResponseList;
    }

    public ProductInfoResponse getProduct(long id) {
        ProductEntity productEntity = getProductById(id);
        ProductInfoResponse productResponse = ProductInfoResponse.fromProductEntity(productEntity);
            productResponse.setImg(getAllPhotoByProduct(productEntity.getId()));
        return productResponse;
    }
    public List<ProductResponse> getProductsByIds(List<Long> ids){
        List<ProductEntity> productEntities = productRepository.findByIdIn(ids);
        return productEntities.stream().map(ProductResponse::fromProductEntity)
                .collect(Collectors.toList());
    }
    public List< byte[]> getAllPhotoByProduct(long id) {
        ProductEntity productEntity = getProductById(id);
            List<ProductImageEntity> productImageEntities = getProductImageByProductEntity(productEntity);
       List< byte[]> resources = fileService.downloadFile(productImageEntities.stream()
                    .map(productImage -> productImage.getUrl()).collect(Collectors.toList()));
        return resources;
    }
    public List<ImageDto> getAllPhotoByProductId(long id) {
        ProductEntity productEntity = getProductById(id);
        List<ProductImageEntity> productImageEntities = getProductImageByProductEntity(productEntity);
        List<ImageDto> imagesDto = new ArrayList<>();
        productImageEntities.forEach(productImage -> {
            ImageDto dto = new ImageDto();
            dto.setId(productImage.getId());
            dto.setUrl(productImage.getUrl());
            dto.setImg(fileService.downloadFile(productImage.getUrl()));
            imagesDto.add(dto);
        });
        return imagesDto;
    }
    public byte[] getFirstPhotoByProduct(long id) {
        ProductEntity productEntity = productRepository.getById(id);
        ProductImageEntity productImageEntities = getFirstProductImageByProductEntity(productEntity);
        byte[] resources = fileService.downloadFile(productImageEntities.getUrl());
        return resources;
    }

    public List<AllProductResponse> getAllProductsBySubCategory(long id) {
        SubCategoryEntity subCategoryEntity = subCategoryService.getSubCategoryById(id);

        List<ProductEntity> productEntityList =productRepository.findBySubCategoryEntity(subCategoryEntity);
        List<AllProductResponse> productResponseList = productEntityList.stream()
                .map(AllProductResponse::fromProductEntity).collect(Collectors.toList());
        productResponseList.forEach(allProductResponse -> allProductResponse.setImg(getFirstPhotoByProduct(allProductResponse.getId())));
        return  productResponseList;
    }

    public List<AllProductResponse> getAllProductsByCategory(long id) {
        Set<SubCategoryEntity> subCategoryEntity = subCategoryRepository.findByCategory_Id(id);

        List<ProductEntity> productEntityList =productRepository.findBySubCategoryEntityIn(subCategoryEntity);
        List<AllProductResponse> productResponseList = productEntityList.stream()
                .map(AllProductResponse::fromProductEntity).collect(Collectors.toList());
        productResponseList.forEach(allProductResponse -> allProductResponse.setImg(getFirstPhotoByProduct(allProductResponse.getId())));
        return  productResponseList;
    }

    public ProductEntity getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product with id " + id + " not found."));
    }

    @Transactional
    @Serializer
    public void saveProduct(AddProductDto addProductDto) {
        addProductDto.getFiles().forEach(file ->
                fileService.uploadFile(file)
        );
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(addProductDto.getName());
        productEntity.setPrice(addProductDto.getPrice());
        productEntity.setDescription(addProductDto.getDescription());
        SubCategoryEntity subCategoryEntity = subCategoryService.getSubCategoryById(addProductDto.getSubCategoryId());
        productEntity.setSubCategoryEntity(subCategoryEntity);
        ProductEntity finalProductEntity = productRepository.save(productEntity);
        Set<ProductImageEntity> productImageEntities = new HashSet<>();
        addProductDto.getFiles().forEach(multipartFile -> {
            ProductImageEntity productImageEntity = new ProductImageEntity();
            productImageEntity.setProductEntity(finalProductEntity);
            productImageEntity.setUrl(fileDirectory + "/" + multipartFile.getOriginalFilename());
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
    public List<ProductImageEntity> getProductImageByProductEntity(ProductEntity productEntity) {
        return productImageRepository.findByProductEntity(productEntity);
    }
    public ProductImageEntity getFirstProductImageByProductEntity(ProductEntity productEntity) {
        return productImageRepository.findFirstByProductEntity(productEntity);
    }

    public ProductImageEntity saveProductImage(ProductImageEntity productImageEntity) {
        return productImageRepository.save(productImageEntity);
    }

    public void deleteProductImageById(Long id) {
        productImageRepository.deleteById(id);
    }
}
