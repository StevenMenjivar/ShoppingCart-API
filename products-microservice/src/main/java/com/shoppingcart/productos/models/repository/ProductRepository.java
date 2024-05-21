package com.shoppingcart.productos.models.repository;

import com.shoppingcart.productos.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
