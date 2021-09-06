package com.example.product_service.controller;

import com.example.product_service.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final Environment env;

    @PostMapping
    public String createProduct(@RequestBody Product product) {
        return "Http POST handled " + product.getTitle();
    }

    @GetMapping
    public String getProduct() {
        return "Http GET handled " + env.getProperty("local.server.port");
    }

    @PutMapping
    public String updateProduct() {
        return "Http PUT handled";
    }

    @DeleteMapping
    public String deleteProduct() {
        return "Http DELETE handled";
    }
}
