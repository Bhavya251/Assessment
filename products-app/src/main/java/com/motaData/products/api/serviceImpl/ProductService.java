package com.motaData.products.api.serviceImpl;

import com.motaData.products.api.Product;
import com.motaData.products.api.dto.ProductCategoryDTO;

import java.util.List;

public interface ProductService {
    List<ProductCategoryDTO> getProducts();
    void addProduct(Product product);

    ProductCategoryDTO getProduct(Long productID);

    boolean deleteProduct(Long productID);

    boolean updateProduct(Long productID, Product product);
}
