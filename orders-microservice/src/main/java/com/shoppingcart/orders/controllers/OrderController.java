package com.shoppingcart.orders.controllers;
import com.shoppingcart.orders.dto.OrderDetailResponseDTO;
import com.shoppingcart.orders.dto.OrderRequestDTO;
import com.shoppingcart.orders.dto.OrderResponseDTO;
import com.shoppingcart.orders.models.entity.Order;
import com.shoppingcart.orders.models.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create-order")
    public OrderResponseDTO createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        Long clienteId = 1L;
        Order orden = orderService.createOrder(orderRequestDTO, clienteId);
        List<OrderDetailResponseDTO> detalleOrdenResponses = orden.getOrderDetails().stream()
                .map(detalle -> new OrderDetailResponseDTO(detalle.getProductId(), detalle.getQuantity(), detalle.getUnitPrice()))
                .collect(Collectors.toList());
        return new OrderResponseDTO(orden.getId(), orden.getClienteId(), orden.getFecha(), orden.getTotal(), orden.getEstado(), detalleOrdenResponses);
    }

    @GetMapping("/orders")
    public List<OrderResponseDTO> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return orders.stream().map(order -> {
            List<OrderDetailResponseDTO> detailResponses = order.getOrderDetails().stream()
                    .map(detail -> new OrderDetailResponseDTO(detail.getProductId(), detail.getQuantity(), detail.getUnitPrice()))
                    .collect(Collectors.toList());

            return new OrderResponseDTO(order.getId(), order.getClienteId(), order.getFecha(), order.getTotal(), order.getEstado(), detailResponses);
        }).collect(Collectors.toList());
    }

    @GetMapping("/orders/{id}")
    public OrderResponseDTO getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);

        if (order == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        List<OrderDetailResponseDTO> detailResponses = order.getOrderDetails().stream()
                .map(detail -> new OrderDetailResponseDTO(detail.getProductId(), detail.getQuantity(), detail.getUnitPrice()))
                .collect(Collectors.toList());
        return new OrderResponseDTO(order.getId(), order.getClienteId(), order.getFecha(), order.getTotal(), order.getEstado(), detailResponses);
    }
    @DeleteMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
    }
}
