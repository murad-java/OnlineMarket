package com.murad.operationsservice.controller;

import com.murad.operationsservice.dto.AddProductDto;
import com.murad.operationsservice.entity.ProductEntity;
import com.murad.operationsservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("/list")
    public List<ProductEntity> getAllProduct(){
        return productService.getAllProducts();
    }
    @GetMapping("/subcategory/{id}")
    public List<ProductEntity> getProductBySubCategory(@PathVariable long id){
        return productService.getAllProductsBySubCategory(id);
    }
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void addProduct(@RequestParam AddProductDto addProductDto){
        productService.saveProduct(addProductDto);
    }
}
