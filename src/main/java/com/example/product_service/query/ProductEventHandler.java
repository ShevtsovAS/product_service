package com.example.product_service.query;

import com.example.product_service.core.data.ProductEntity;
import com.example.product_service.core.data.ProductsRepository;
import com.example.product_service.core.events.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductEventHandler {

    private final ProductsRepository productsRepository;

    @SuppressWarnings("unused")
    @EventHandler
    public void on(ProductCreatedEvent event) {
        val productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);

        productsRepository.save(productEntity);
    }
}
