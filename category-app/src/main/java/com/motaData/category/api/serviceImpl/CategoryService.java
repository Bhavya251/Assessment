package com.motaData.category.api.serviceImpl;

import com.motaData.category.api.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    void addCategory(Category category);
    Category getCategory(Long id);
    boolean deleteCategory(Long id);
    boolean updateCategory(Long id, Category category);
}
