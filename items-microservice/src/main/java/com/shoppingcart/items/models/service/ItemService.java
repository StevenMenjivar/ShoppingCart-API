package com.shoppingcart.items.models.service;

import com.shoppingcart.items.models.Item;
import com.shoppingcart.items.models.Product;

import java.util.List;

public interface ItemService {
    //PARA LISTAR TODOS LOS ITEMS
    public List<Item> findAll();
    public Item findById(Long id, Integer quantity);

    //OBJETO A CREAR
    public Product save(Product product);
    public Product update(Product product, Long id);

    public  void delete(Long id);
}
