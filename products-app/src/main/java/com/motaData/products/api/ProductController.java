package com.motaData.products.api;

import com.motaData.products.api.dto.ProductCategoryDTO;
import com.motaData.products.api.serviceImpl.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Operation(summary = "Get all products")
    @GetMapping("/v1.0")
    public ResponseEntity<List<ProductCategoryDTO>> getProducts() {
        if(productService.getProducts() == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @Operation(summary = "Add a new product")
    @PostMapping("/v1.0")
    public ResponseEntity<String> addProduct(@RequestBody @Valid Product product) {
        productService.addProduct(product);
        return new ResponseEntity<>("New Product Added !", HttpStatus.CREATED);
    }

    @Operation(summary = "Delete product by ID")
    @DeleteMapping("/v1.0/{productID}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productID) {
        boolean result = productService.deleteProduct(productID);
        if (!result) {
            return new ResponseEntity<>("Product Not Found !", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Product Deleted !", HttpStatus.OK);
    }

    @Operation(summary = "Get a product by ID")
    @GetMapping("/v1.0/{productID}")
    public ResponseEntity<ProductCategoryDTO> getProduct(@PathVariable Long productID) {
        if(productService.getProduct(productID) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productService.getProduct(productID), HttpStatus.OK);
    }

    @Operation(summary = "Update an existing product")
    @PutMapping("/v1.0/{productID}")
    public ResponseEntity<String> updateProduct(@PathVariable Long productID, @RequestBody @Valid Product product) {
        boolean result = productService.updateProduct(productID, product);
        if (!result) {
            return new ResponseEntity<>("Product Not Found !", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Product Updated !", HttpStatus.OK);
    }
}
