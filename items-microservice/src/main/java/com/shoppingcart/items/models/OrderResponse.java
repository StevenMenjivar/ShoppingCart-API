package com.shoppingcart.items.models;

import java.util.List;

public class OrderResponse {
    private List<Item> items;
    private Double totalOrder;

    public OrderResponse(List<Item> items, Double totalOrder) {
        this.items = items;
        this.totalOrder = totalOrder;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Double getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(Double totalOrder) {
        this.totalOrder = totalOrder;
    }
}
