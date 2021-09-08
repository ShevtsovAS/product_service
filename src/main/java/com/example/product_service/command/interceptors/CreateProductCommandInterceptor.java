package com.example.product_service.command.interceptors;

import com.example.product_service.command.CreateProductCommand;
import com.example.product_service.core.data.ProductLookupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private final ProductLookupRepository productLookupRepository;

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> list) {
        return (index, command) -> {
            log.info("Intercepted command: {}", command.getPayloadType());
            if (CreateProductCommand.class.equals(command.getPayloadType())) {
                val createProductCommand = (CreateProductCommand) command.getPayload();
                val alreadyExists = productLookupRepository.existsByProductIdOrTitle(createProductCommand.getProductId(), createProductCommand.getTitle());
                if (alreadyExists) {
                    throw new IllegalStateException(String.format("Product with productId %s or title %s already exists",
                            createProductCommand.getProductId(), createProductCommand.getTitle()));
                }
            }
            return command;
        };
    }
}
