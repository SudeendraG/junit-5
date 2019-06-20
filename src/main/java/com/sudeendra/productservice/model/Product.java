package com.sudeendra.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sudeendra
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;
    private String name;
    private Integer quantity;
    private Integer version;

    public Product(String product_name, int id) {
    }

    public Product(int i, String product_name, int i1) {
    }
}
