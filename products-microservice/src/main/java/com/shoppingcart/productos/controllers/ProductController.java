package com.shoppingcart.productos.controllers;

import com.shoppingcart.productos.models.entity.Product;
import com.shoppingcart.productos.models.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {
    @Autowired
    private Environment env;
    @Autowired
    private IProductService productService;
    //Retornando lista de productos
    @GetMapping("/listar")
    public List<Product> listar(){
        return productService.findAll().stream().map(product -> {
            product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
            return product;
        }).collect(Collectors.toList());
    }
    @GetMapping("/ver/{id}")
    public Product detail(@PathVariable Long id){
        Product product = productService.findById(id);
                product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
          return product;
    }
    //Para crear producto JSON
    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Product crear(@RequestBody Product product){
        return productService.save(product);
    }
    //EDITAR PRODUCTO
    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product editar(@RequestBody Product product, @PathVariable Long id){
        Product productDB= productService.findById(id);
        productDB.setName(product.getName());
        product.setPrice(product.getPrice());
        return productService.save(productDB);
    }
    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id){
        productService.deleteById(id);
    }

}
