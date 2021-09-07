package com.example.product_service.command.rest;

import com.example.product_service.command.CreateProductCommand;
import com.example.product_service.model.Product;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductCommandController {

    private final Environment env;
    private final CommandGateway commandGateway;

    @PostMapping
    public String createProduct(@RequestBody Product product) {
        try {
            return commandGateway.sendAndWait(CreateProductCommand.builder()
                    .title(product.getTitle())
                    .quantity(product.getQuantity())
                    .price(product.getPrice())
                    .productId(UUID.randomUUID().toString())
                    .build());
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }
    }

//    @GetMapping
//    public String getProduct() {
//        return "Http GET handled " + env.getProperty("local.server.port");
//    }
//
//    @PutMapping
//    public String updateProduct() {
//        return "Http PUT handled";
//    }
//
//    @DeleteMapping
//    public String deleteProduct() {
//        return "Http DELETE handled";
//    }
}
