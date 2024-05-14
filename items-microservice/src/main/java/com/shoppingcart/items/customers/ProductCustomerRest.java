package com.shoppingcart.items.customers;

import com.shoppingcart.items.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "api-shopping-cart-productos")
//@FeignClient(name = "api-shopping-cart-productos", url ="localhost:8001")
public interface ProductCustomerRest {
    //Listar productos
    @GetMapping("/listar")
    public List<Product> listar();

    //importando metodo-no implementacion
    @GetMapping("/ver/{id}")
    public Product detail(@PathVariable Long id);

    //Para consumir con el cliente Feign
    @PostMapping("/crear")
    public Product crear(@RequestBody Product product);

    @PutMapping("/editar/{id}")
    public Product update(@RequestBody Product product, @PathVariable Long id);
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id);

}
