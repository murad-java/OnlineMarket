package com.murad.operationsservice.controller;

import com.murad.operationsservice.dto.AddProductDto;
import com.murad.operationsservice.dto.AllProductResponse;
import com.murad.operationsservice.dto.ImageDto;
import com.murad.operationsservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.OPTIONS;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:8080","http://localhost:3000","http://192.168.1.123:8080"})
public class ProductController {
    private final ProductService productService;
    @GetMapping(path = "/list")
    public List<AllProductResponse>  getAllProduct() {
        List<AllProductResponse> resources = productService.getAllProducts();

        return resources;
    }
    @GetMapping(path = "/img/{id}")
    public List<ImageDto>  getAllImages(@PathVariable long id) {
        List<ImageDto> resources = productService.getAllPhotoByProductId(id);

        return resources;
    }
    @GetMapping("/subcategory/{id}")
    public ResponseEntity<List<AllProductResponse>> getProductBySubCategory(@PathVariable long id){
        return ResponseEntity.ok().body(productService.getAllProductsBySubCategory(id));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<AllProductResponse>> getProductBySubCategories(@PathVariable long id){
        return ResponseEntity.ok().body(productService.getAllProductsByCategory(id));
    }
    @PostMapping(path = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void addProduct(@ModelAttribute AddProductDto addProductDto){
        productService.saveProduct(addProductDto);
    }


}
