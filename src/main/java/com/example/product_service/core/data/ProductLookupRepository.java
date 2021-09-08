package com.example.product_service.core.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLookupRepository extends JpaRepository<ProductLookupEntity, String> {
    boolean existsByProductIdOrTitle(String productId, String title);
}
