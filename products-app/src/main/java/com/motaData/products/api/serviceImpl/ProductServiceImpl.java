package com.motaData.products.api.serviceImpl;

import com.motaData.products.api.Product;
import com.motaData.products.api.ProductRepository;
import com.motaData.products.api.client.CategoryClient;
import com.motaData.products.api.dto.ProductCategoryDTO;
import com.motaData.products.api.external.Category;
import com.motaData.products.api.mapper.ProductCategoryMapper;
import com.motaData.products.producer.KafkaProducerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepo;

    private KafkaProducerService kafkaProducerService;

    private CategoryClient categoryClient;

    public ProductServiceImpl(ProductRepository productRepo, KafkaProducerService kafkaProducerService, CategoryClient categoryClient) {
        this.productRepo = productRepo;
        this.kafkaProducerService = kafkaProducerService;
        this.categoryClient = categoryClient;
    }

    @Override
    public List<ProductCategoryDTO> getProducts() {

        List<Product> products = productRepo.findAll();

        List<ProductCategoryDTO> productCategoryDTOS;
        productCategoryDTOS = products.stream().map(this::covertToDTO).collect(Collectors.toList());
        return productCategoryDTOS;
    }

    @Override
    public void addProduct(Product product) {
        productRepo.save(product);
        kafkaProducerService.sendMessage("Product created: " + "ProductID-" + product.getProductName());
    }

    @Override
    public ProductCategoryDTO getProduct(Long productID) {

        if (productRepo.findById(productID).isEmpty()) {
            return null;
        }
        Product product = productRepo.findById(productID).get();
        return covertToDTO(product);
    }

    private ProductCategoryDTO covertToDTO(Product product) {

        Category category = categoryClient.getCategory(product.getCategoryID());
        ProductCategoryDTO productCategoryDTO = ProductCategoryMapper.mapToProductCategoryDTO(product, category);
        productCategoryDTO.setCategory(category);

        return productCategoryDTO;
    }

    @Override
    public boolean deleteProduct(Long productID) {
        Optional<Product> product = productRepo.findById(productID);

        if (product.isPresent()) {
            productRepo.delete(product.get());
            kafkaProducerService.sendMessage("Product deleted: " + "ProductID-" + productID);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateProduct(Long productID, Product productUpdated) {
        Optional<Product> productOptional = productRepo.findById(productID);

        if (productOptional.isPresent()) {

            Product product = productOptional.get();
            product.setProductName(productUpdated.getProductName());
            product.setProductDescription(productUpdated.getProductDescription());
            product.setCategoryID(productUpdated.getCategoryID());

            productRepo.save(product);
            kafkaProducerService.sendMessage("Product updated: " + "ProductID-" + productID);
            return true;
        }
        return false;
    }
}
