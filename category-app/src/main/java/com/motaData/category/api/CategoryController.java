package com.motaData.category.api;

import com.motaData.category.api.serviceImpl.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Get all categories")
    @GetMapping("/v1.0")
    public ResponseEntity<List<Category>> getCategories(){
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
    }

    @Operation(summary = "Add a new category")
    @PostMapping("/v1.0")
    public ResponseEntity<String> addCategory(@RequestBody Category category){
        categoryService.addCategory(category);
        return new ResponseEntity<>("New Category Added !", HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a category by ID")
    @DeleteMapping("/v1.0/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        boolean result = categoryService.deleteCategory(id);
        if (!result) {
            return new ResponseEntity<>("Category Not Found !", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Category Deleted !", HttpStatus.OK);

    }


    @Operation(summary = "Get a category by ID")
    @GetMapping("/v1.0/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id){
        Category category = categoryService.getCategory(id);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryService.getCategory(id), HttpStatus.OK);
    }

    @Operation(summary = "Update an existing category")
    @PutMapping("/v1.0/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody Category category){
        boolean result = categoryService.updateCategory(id, category);
        if (!result) {
            return new ResponseEntity<>("Category Not Found !", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Category Updated !", HttpStatus.OK);
    }

}
