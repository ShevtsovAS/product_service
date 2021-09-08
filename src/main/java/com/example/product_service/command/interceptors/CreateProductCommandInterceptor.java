package com.example.product_service.command.interceptors;

import com.example.product_service.command.CreateProductCommand;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

@Component
@Slf4j
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> list) {
        return (index, command) -> {
            log.info("Intercepted command: {}", command.getPayloadType());
            if (CreateProductCommand.class.equals(command.getPayloadType())) {
                var createProductCommand = (CreateProductCommand) command.getPayload();
                Optional.of(createProductCommand.getPrice()).filter(it -> it.compareTo(BigDecimal.ZERO) > 0)
                        .orElseThrow(() -> new IllegalArgumentException("Price cannot be less or equal than zero"));
                Optional.of(createProductCommand.getTitle()).filter(StringUtils::isNotBlank)
                        .orElseThrow(() -> new IllegalArgumentException("Title cannot be empty"));
            }
            return command;
        };
    }
}
