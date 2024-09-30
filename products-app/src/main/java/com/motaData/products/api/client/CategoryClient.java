package com.motaData.products.api.client;

import com.motaData.products.api.external.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "category", url = "http://localhost:8092")
public interface CategoryClient {

    @GetMapping("/category/v1.0/{id}")
    public Category getCategory(@PathVariable("id") Long categoryId);
}
