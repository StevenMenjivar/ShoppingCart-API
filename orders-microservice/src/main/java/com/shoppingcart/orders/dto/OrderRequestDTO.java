package com.shoppingcart.orders.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {
    private List<OrderDetailRequestDTO> order;
//    public List<OrderDetailRequestDTO> getOrder() {
//        return order;
//    }
//    public void setOrder(List<OrderDetailRequestDTO> order) {
//        this.order = order;
//    }
//    private Double totalOrder;
//
//    public List<OrderDetailRequestDTO> getOrder() {
//        return order;
//    }
//
//    public void setOrder(List<OrderDetailRequestDTO> order) {
//        this.order = order;
//    }
//
//    public Double getTotalOrder() {
//        return totalOrder;
//    }
//
//    public void setTotalOrder(Double totalOrder) {
//        this.totalOrder = totalOrder;
//    }
}

