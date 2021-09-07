package com.example.product_service.command.rest;

import com.example.product_service.command.CreateProductCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductCommandController {

    private final Environment env;
    private final CommandGateway commandGateway;

    @PostMapping
    public String createProduct(@Valid @RequestBody CreateProductRestModel createProductRestModel) {
        try {
            return commandGateway.sendAndWait(CreateProductCommand.builder()
                    .title(createProductRestModel.getTitle())
                    .quantity(createProductRestModel.getQuantity())
                    .price(createProductRestModel.getPrice())
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
