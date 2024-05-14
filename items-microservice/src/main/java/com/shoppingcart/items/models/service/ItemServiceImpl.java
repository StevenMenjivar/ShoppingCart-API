package com.shoppingcart.items.models.service;

import com.shoppingcart.items.models.Item;
import com.shoppingcart.items.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService{
    @Autowired
    private RestTemplate clientRest;
    @Override
    public List<Item> findAll() {
        //accediendo a endpoint listar
        List<Product> products = Arrays.asList(clientRest.getForObject("http://api-shopping-cart-productos/listar",Product[].class));
        return products.stream().map(p -> new Item(p,1)).collect(Collectors.toList());
    }
    @Override
    public Item findById(Long id, Integer quantity) {
        //map para pasar id
        Map<String, String> pathVariables=new HashMap<String,String>();
        pathVariables.put("id",id.toString());
        Product product=clientRest.getForObject("http://api-shopping-cart-productos/ver/{id}", Product.class, pathVariables);
        return new Item(product,quantity);
    }

    @Override
    public Product save(Product product) {
        HttpEntity<Product> body = new HttpEntity<Product>(product);
        ResponseEntity<Product> response= clientRest.exchange("http://api-shopping-cart-productos/crear", HttpMethod.POST, body, Product.class);
        Product productResponse=response.getBody();
        return productResponse;
    }

    @Override
    public Product update(Product product, Long id) {
        Map<String, String> pathVariables=new HashMap<String,String>();
        pathVariables.put("id",id.toString());
        HttpEntity<Product> body = new HttpEntity<Product>(product);
        ResponseEntity<Product> response = clientRest.exchange("http://api-shopping-cart-productos/editar/{id}",HttpMethod.PUT,body, Product.class, pathVariables);
        return response.getBody();
    }

    @Override
    public void delete(Long id) {
        Map<String, String> pathVariables=new HashMap<String,String>();
        pathVariables.put("id",id.toString());
        clientRest.delete("http://api-shopping-cart-productos/eliminar/{id}",pathVariables);
    }
}
