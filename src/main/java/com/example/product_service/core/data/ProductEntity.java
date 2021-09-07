package com.example.product_service.core.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "products")
public class ProductEntity implements Serializable {

    private static final long serialVersionUID = 4265768434L;

    @Id
    @Column(unique = true)
    private String productId;

    private String title;
    private BigDecimal price;
    private Integer quantity;

}
