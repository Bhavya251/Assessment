package com.motaData.products.api.mapper;

import com.motaData.products.api.Product;
import com.motaData.products.api.dto.ProductCategoryDTO;
import com.motaData.products.api.external.Category;

public class ProductCategoryMapper {
    public static ProductCategoryDTO mapToProductCategoryDTO(Product product, Category category) {

        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();

        productCategoryDTO.setProductID(product.getProductID());
        productCategoryDTO.setProductName(product.getProductName());
        productCategoryDTO.setProductDescription(product.getProductDescription());
        productCategoryDTO.setCategory(category);

        return productCategoryDTO;
    }
}
