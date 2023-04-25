package com.murad.operationsservice.controller;

import com.murad.operationsservice.dto.SubCategoryDto;
import com.murad.operationsservice.dto.SubCategoryResponse;
import com.murad.operationsservice.dto.SubCategoryUpdateDto;
import com.murad.operationsservice.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/subcategories")
@RequiredArgsConstructor
//@CrossOrigin(origins = {"http://localhost:8080","http://localhost:3000","http://192.168.1.123:8080"})
public class SubCategoryController {
    private final SubCategoryService subCategoryService;

    @GetMapping("/{id}")
    public SubCategoryResponse getSubCategoryById(@PathVariable Long id) {
        return subCategoryService.getSubCategoryByIdForResponse(id);
    }

    @GetMapping("/by-category/{categoryId}")
    public Set<SubCategoryResponse> getSubCategoriesByCategoryId(@PathVariable Long categoryId) {
        return subCategoryService.getSubCategoryByCategoryId(categoryId);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void createSubCategory(@RequestBody SubCategoryDto dto) {
        subCategoryService.createSubCategory(dto);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void updateSubCategory( @RequestBody SubCategoryUpdateDto dto) {
        subCategoryService.updateSubCategory(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteSubCategory(@PathVariable Long id) {
        subCategoryService.deleteSubCategory(id);
    }
}
