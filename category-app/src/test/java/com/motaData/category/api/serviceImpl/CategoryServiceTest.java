package com.motaData.category.api.serviceImpl;

import com.motaData.category.api.Category;
import com.motaData.category.api.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;


    @Test
    void deleteCategory() {
        boolean result = categoryService.deleteCategory(999L);
        assertFalse(result);
    }

    @Test
    void updateCategory() {
        Category category = new Category();
        category.setCategoryName("Home Appliances");

        boolean result = categoryService.updateCategory(999L, category);
        assertFalse(result);
    }

    @Test
    void getCategory() {
        Category category = new Category();
        category.setCategoryName("Electronics");
        categoryService.addCategory(category);

        Category fetchedCategory = categoryService.getCategory(category.getCategoryID());
        assertNotNull(fetchedCategory);
        assertEquals("Electronics", fetchedCategory.getCategoryName());
    }
}