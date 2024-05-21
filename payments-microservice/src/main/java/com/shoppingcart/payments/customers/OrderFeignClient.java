package com.shoppingcart.payments.customers;

import com.shoppingcart.payments.dto.OrderResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "orders-microservice")
public interface OrderFeignClient {
    @GetMapping("/orders/{id}")
    OrderResponseDTO getOrderById(@PathVariable("id") Long id);

}
