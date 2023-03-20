package com.murad.operationsservice.dto;

import com.murad.operationsservice.entity.CategoryEntity;
import lombok.Data;

import java.util.Objects;

@Data
public class CategoriesResponse {
   private Long id;
   private String name;

   public static CategoriesResponse fromCategoryEntity(CategoryEntity categoryEntity){
       CategoriesResponse response =new CategoriesResponse();
       response.setId(categoryEntity.getId());
       response.setName(categoryEntity.getName());
       return response;
   }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriesResponse that = (CategoriesResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
