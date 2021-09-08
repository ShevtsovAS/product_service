package com.example.product_service.command.rest;

import com.example.product_service.command.CreateProductCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductCommandController {

    private final CommandGateway commandGateway;

    @PostMapping
    public String createProduct(@Valid @RequestBody CreateProductRestModel createProductRestModel) {
        return commandGateway.sendAndWait(CreateProductCommand.builder()
                .title(createProductRestModel.getTitle())
                .quantity(createProductRestModel.getQuantity())
                .price(createProductRestModel.getPrice())
                .productId(UUID.randomUUID().toString())
                .build());
    }
}
