package com.example.product_service.query;

import com.example.product_service.core.data.ProductsRepository;
import com.example.product_service.query.rest.ProductRestModel;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class ProductsQueryHandler {

    private final ProductsRepository productsRepository;

    @SuppressWarnings("unused")
    @QueryHandler
    public List<ProductRestModel> findProducts(FindProductsQuery query) {
        return productsRepository.findAll().stream()
                .map(productEntity -> {
                    ProductRestModel productRestModel = new ProductRestModel();
                    BeanUtils.copyProperties(productEntity, productRestModel);
                    return productRestModel;
                }).collect(toList());
    }

}
