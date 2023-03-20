package com.murad.operationsservice.controler;

import com.murad.operationsservice.dto.AddCategoryDto;
import com.murad.operationsservice.dto.UpdateCategoryDto;
import com.murad.operationsservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllCategory(){
        return ResponseEntity.ok(categoryService.getAll());
    }
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> addCategory(@RequestBody AddCategoryDto category){
        categoryService.addCategory(category);
        return ResponseEntity.ok(getAllCategory());
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateCategory(@RequestBody UpdateCategoryDto category){
        categoryService.updateCategory(category);
        return ResponseEntity.ok(getAllCategory());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(getAllCategory());
    }
}
