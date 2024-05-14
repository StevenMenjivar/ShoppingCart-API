package com.shoppingcart.items.models.service;

import com.shoppingcart.items.models.Item;
import com.shoppingcart.items.models.entity.Order;
import com.shoppingcart.items.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
//    private final List<Order> orders = new ArrayList<>();
//
//    public List<Order> getAllOrders() {
//        return orders;
//    }
//
//    public void createOrder(Order order) {
//        orders.add(order);
//    }


    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        // Persistir la orden en la base de datos
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }




//private final OrderRepository orderRepository;
//
//    @Autowired
//    public OrderService(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }
//
//    private final List<Order> orders = new ArrayList<>();
//        public List<Order> getAllOrders() {
//        return orders;
//    }
//
//    public Order createOrder(Order order) {
//        // Establecer la relación entre la orden y los ítems
//        for (Item item : order.getItems()) {
//            item.setOrder(order);
//        }
//
//        // Guardar la orden en la base de datos
//        return orderRepository.save(order);
//    }
}
