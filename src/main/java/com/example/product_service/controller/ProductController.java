package com.example.product_service.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @PostMapping
    public String createProduct() {
        return "Http POST handled";
    }

    @GetMapping
    public String getProduct() {
        return "Http GET handled";
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
