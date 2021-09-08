package com.example.product_service.command;

import com.example.product_service.core.data.ProductLookupEntity;
import com.example.product_service.core.data.ProductLookupRepository;
import com.example.product_service.core.events.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
@RequiredArgsConstructor
public class ProductLookupEventHandler {

    private final ProductLookupRepository productLookupRepository;

    @SuppressWarnings("unused")
    @EventHandler
    public void on(ProductCreatedEvent event) {
        val productLookupEntity = new ProductLookupEntity(event.getProductId(), event.getTitle());

        productLookupRepository.save(productLookupEntity);
    }
}
