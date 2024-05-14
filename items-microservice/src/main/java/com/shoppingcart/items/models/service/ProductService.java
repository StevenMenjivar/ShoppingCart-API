package com.shoppingcart.items.models.service;

import com.shoppingcart.items.models.Product;

import java.util.List;

public interface ProductService {
    Product findById(Long id);
    List<Product> findAll();
    Product save(Product product);
    Product update(Product product);
    void delete(Long id);
}
