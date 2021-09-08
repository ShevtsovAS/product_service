package com.example.product_service.query;

import com.example.product_service.core.data.ProductEntity;
import com.example.product_service.core.data.ProductsRepository;
import com.example.product_service.core.events.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@ProcessingGroup("product-group")
public class ProductEventHandler {

    private final ProductsRepository productsRepository;

    @SuppressWarnings("unused")
    @EventHandler
    public void on(ProductCreatedEvent event) throws Exception {
        val productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);

        try {
            productsRepository.save(productEntity);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        if (true) throw new Exception("Forcing exception in the Event Handler class");

    }

    @SuppressWarnings("unused")
    @ExceptionHandler(resultType = IllegalArgumentException.class)
    public void handle(IllegalArgumentException exception) {
        log.error(exception.getMessage());
        throw exception;
    }

    @SuppressWarnings("unused")
    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        log.error(exception.getMessage());
        throw exception;
    }
}
