package com.shoppingcart.productos.models.dao;

import com.shoppingcart.productos.models.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDao extends CrudRepository<Product,Long> {

}
