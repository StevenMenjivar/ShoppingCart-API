package com.shoppingcart.orders.repository;

import com.shoppingcart.orders.models.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository <OrderDetail, Long> {
}
