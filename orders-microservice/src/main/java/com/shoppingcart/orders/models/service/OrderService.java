package com.shoppingcart.orders.models.service;

import com.shoppingcart.orders.clients.ProductClientRest;
import com.shoppingcart.orders.dto.OrderDetailRequestDTO;
import com.shoppingcart.orders.models.entity.OrderDetail;
import com.shoppingcart.orders.dto.OrderRequestDTO;
import com.shoppingcart.orders.models.Product;
import com.shoppingcart.orders.models.entity.Order;
import com.shoppingcart.orders.repository.OrderDetailRepository;
import com.shoppingcart.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductClientRest productClient;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, ProductClientRest productClient) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.productClient = productClient;
    }

    public Order createOrder(OrderRequestDTO orderRequestDTO, Long clienteId) {
        List<OrderDetail> detalleOrdenList = new ArrayList<>();
        double totalOrder = 0.0;

        for (OrderDetailRequestDTO orderDetailRequestDTO : orderRequestDTO.getOrder()) {
            Product producto = productClient.viewProduct(orderDetailRequestDTO.getIdProduct());
            if (producto != null) {
                OrderDetail detalleOrden = new OrderDetail();
                detalleOrden.setProductId(producto.getId());
                detalleOrden.setQuantity(orderDetailRequestDTO.getQuantity());
                detalleOrden.setUnitPrice(producto.getPrice());
                detalleOrdenList.add(detalleOrden);
                totalOrder += detalleOrden.getUnitPrice() * orderDetailRequestDTO.getQuantity();
            } else {
                throw new RuntimeException("Product with ID " + orderDetailRequestDTO.getIdProduct() + " does not exist");
            }
        }

            Order orden = new Order();
            orden.setClienteId(clienteId);
            orden.setFecha(new Date());
            orden.setTotal(totalOrder);
            orden.setEstado("PENDIENTE");
            orden.setOrderDetails(detalleOrdenList);
            Order savedOrder = orderRepository.save(orden);

        for (OrderDetail detalle : detalleOrdenList) {
            detalle.setOrder(savedOrder);
        }
        orderDetailRepository.saveAll(detalleOrdenList);

        return savedOrder;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void deleteOrderById(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The order was not found");
        }
    }
}
