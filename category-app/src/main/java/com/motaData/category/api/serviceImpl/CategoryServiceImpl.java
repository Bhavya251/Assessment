package com.motaData.category.api.serviceImpl;

import com.motaData.category.api.Category;
import com.motaData.category.api.CategoryRepository;
import com.motaData.category.producer.KafkaProducerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    private KafkaProducerService kafkaProducerService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, KafkaProducerService kafkaProducerService) {
        this.categoryRepository = categoryRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
        kafkaProducerService.sendMessage("Category created: " + "CategoryID-" + category.getCategoryID());
    }

    @Override
    public Category getCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    @Override
    public boolean deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            categoryRepository.deleteById(id);
            kafkaProducerService.sendMessage("Category deleted: " + "CategoryID-" + id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCategory(Long id, Category categoryUpdated) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();

            category.setCategoryName(categoryUpdated.getCategoryName());

            categoryRepository.save(category);

            kafkaProducerService.sendMessage("Category updated: " + "CategoryID-" + id);
            return true;
        }
        return false;
    }

}
