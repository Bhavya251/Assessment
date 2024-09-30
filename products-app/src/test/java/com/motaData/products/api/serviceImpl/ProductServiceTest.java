package com.motaData.products.api.serviceImpl;

import com.motaData.products.api.Product;
import com.motaData.products.api.dto.ProductCategoryDTO;
import com.motaData.products.api.external.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Rollback
class ProductServiceTest {

    @Autowired
    private ProductService productService;


    @Test
    void getProduct() {
        Product product = new Product();
        product.setProductName("Phone");
        product.setProductDescription("Smartphone");
        product.setCategoryID(1L);
        productService.addProduct(product);

        ProductCategoryDTO fetchedProduct = productService.getProduct(product.getProductID());
        assertNotNull(fetchedProduct);
        assertEquals("Phone", fetchedProduct.getProductName());
    }

    @Test
    void deleteProduct() {
        boolean result = productService.deleteProduct(999L);
        assertFalse(result);
    }
}