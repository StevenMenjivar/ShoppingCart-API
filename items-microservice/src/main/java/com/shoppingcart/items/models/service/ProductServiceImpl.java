package com.shoppingcart.items.models.service;

import com.shoppingcart.items.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Product findById(Long id) {
        return restTemplate.getForObject("http://api-shopping-cart-productos/ver/{id}", Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        ResponseEntity<Product[]> responseEntity = restTemplate.getForEntity("http://api-shopping-cart-productos/listar", Product[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public Product save(Product product) {
        ResponseEntity<Product> responseEntity = restTemplate.postForEntity("http://api-shopping-cart-productos/crear", product, Product.class);
        return responseEntity.getBody();
    }

    @Override
    public Product update(Product product) {
        restTemplate.put("http://api-shopping-cart-productos/editar/{id}", product, product.getId());
        return product;
    }

    @Override
    public void delete(Long id) {
        restTemplate.delete("http://api-shopping-cart-productos/eliminar/{id}", id);
    }
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Override
//    public Product findById(Long id) {
//        return restTemplate.getForObject("http://api-shopping-cart-productos/ver/{id}", Product.class, id);
//    }
//
//    @Override
//    public List<Product> findAll() {
//        return Arrays.asList(restTemplate.getForObject("http://api-shopping-cart-productos/listar", Product[].class));
//    }
//
//    @Override
//    public Product save(Product product) {
//        return restTemplate.postForObject("http://api-shopping-cart-productos/crear", product, Product.class);
//    }
//
//    @Override
//    public Product update(Product product) {
//        restTemplate.put("http://api-shopping-cart-productos/editar/{id}", product, product.getId());
//        return product;
//    }
//
//    @Override
//    public void delete(Long id) {
//        restTemplate.delete("http://api-shopping-cart-productos/eliminar/{id}", id);
//    }
}
