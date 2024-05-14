package com.shoppingcart.items.models;

import java.util.List;

public class OrderRequest {
    private List<OrderItem> order;
    private Double totalOrder;

    public List<OrderItem> getOrder() {
        return order;
    }

    public void setOrder(List<OrderItem> order) {
        this.order = order;
    }

    public Double getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(Double totalOrder) {
        this.totalOrder = totalOrder;
    }
}

