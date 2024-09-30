package com.motaData.products.api;

import com.motaData.products.api.external.Category;
import jakarta.persistence.*;

@Entity
@Table(name = "master_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;
    private String productName;
    private String productDescription;

    private Long categoryID;


    public Product(Long productID, String productName, String productDescription, Long categoryID) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.categoryID = categoryID;
    }

    public Product() {

    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }
}
