package com.shoppingcart.items.models.service;

import com.shoppingcart.items.customers.ProductCustomerRest;
import com.shoppingcart.items.models.Item;
import com.shoppingcart.items.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service("serviceFeign")
//inyecandose en el controllable itemService
public class ItemServiceFeign implements ItemService{
    @Autowired
    private ProductCustomerRest customerFeign;
    @Override
    public List<Item> findAll() {
        return customerFeign.listar().stream().map(p -> new Item(p,1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer quantity) {
        return  new Item(customerFeign.detail(id),quantity) ;
    }
    ///PARA CRUD
    @Override
    public Product save(Product product) {
        return customerFeign.crear(product);
    }

    @Override
    public Product update(Product product, Long id) {
        return customerFeign.update(product, id);
    }
    @Override
    public void delete(Long id) {
        customerFeign.eliminar(id);
    }
}
