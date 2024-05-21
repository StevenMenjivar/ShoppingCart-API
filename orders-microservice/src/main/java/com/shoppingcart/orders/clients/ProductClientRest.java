package com.shoppingcart.orders.clients;

import com.shoppingcart.orders.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "api-shopping-cart-productos")
public interface ProductClientRest {
    //Listar productos
    @GetMapping("/products")
    public List<Product> allProducts();

    //importando metodo-no implementacion
    @GetMapping("/products/view/{id}")
    public Product viewProduct(@PathVariable Long id);

    //Para consumir con el cliente Feign
    @PostMapping("/create-product")
    public Product createProduct(@RequestBody Product product);

    @PutMapping("/update-product/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable Long id);
    @DeleteMapping("/delete-product/{id}")
    public void deleteProduct(@PathVariable Long id);

}
