package com.shoppingcart.items.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shoppingcart.items.models.*;
import com.shoppingcart.items.models.entity.Order;
import com.shoppingcart.items.models.service.ItemService;
import com.shoppingcart.items.models.service.OrderService;
import com.shoppingcart.items.models.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    @Qualifier("serviceFeign")
    private ItemService itemService;
    //ORDER
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    //GET ORDERS
    @GetMapping("/ordenes")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/listar")
    public List<Item> listar(){
        return itemService.findAll();
    }

    @GetMapping("/ver/{id}/quantity/{quantity}")
    public Item detail(@PathVariable Long id, @PathVariable Integer quantity){
        return itemService.findById(id,quantity);

    }
    //IMPLEMENTANDO METODOS EN CONTROLLER
    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Product crear(@RequestBody Product product){
        return itemService.save(product);
    }
    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product editar(@RequestBody Product product, @PathVariable Long id){
        return itemService.update(product, id);
    }
    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
        public void eliminar(@PathVariable Long id){
            itemService.delete(id);
        }
    //CREAR ORDEN
    @PostMapping("/crear-orden")
    public OrderResponse crearOrden(@RequestBody OrderRequest orderRequest) {
        List<Item> items = new ArrayList<>();
        double totalOrder = 0.0;

        for (OrderItem orderItem : orderRequest.getOrder()) {
            Product product = productService.findById(orderItem.getIdProduct());
            if (product != null) {
                items.add(new Item(product, orderItem.getQuantity()));
                totalOrder += product.getPrice() * orderItem.getQuantity();
            }
        }
        Order order = new Order(items, totalOrder);
        return new OrderResponse(order.getItems(), order.getTotalOrder());
    }
}
