package com.example.product_service.core.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "productlookup")
public class ProductLookupEntity implements Serializable {

    static final long serialVersionUID = 4654654652L;

    @Id
    private String productId;

    @Column(unique = true)
    private String title;

}
