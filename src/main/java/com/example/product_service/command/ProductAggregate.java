package com.example.product_service.command;

import com.example.product_service.core.events.ProductCreatedEvent;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Optional;

@Aggregate
@NoArgsConstructor
@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        validate(createProductCommand);
        AggregateLifecycle.apply(buildProductCreatedEvent(createProductCommand));
    }

    @SuppressWarnings("unused")
    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        productId = productCreatedEvent.getProductId();
        price = productCreatedEvent.getPrice();
        title = productCreatedEvent.getTitle();
        quantity = productCreatedEvent.getQuantity();
    }

    private ProductCreatedEvent buildProductCreatedEvent(CreateProductCommand createProductCommand) {
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        BeanUtils.copyProperties(createProductCommand, productCreatedEvent);
        return productCreatedEvent;
    }

    private void validate(CreateProductCommand createProductCommand) {
        Optional.of(createProductCommand.getPrice()).filter(it -> it.compareTo(BigDecimal.ZERO) > 0)
                .orElseThrow(() -> new IllegalArgumentException("Price cannot be less or equal than zero"));
        Optional.of(createProductCommand.getTitle()).filter(StringUtils::isNotBlank)
                .orElseThrow(() -> new IllegalArgumentException("Title cannot be empty"));
    }
}
