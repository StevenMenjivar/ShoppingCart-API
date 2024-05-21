package com.shoppingcart.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDetailResponseDTO {
    private Long idProduct;
    private Integer quantity;
    private Double unitPrice;
//    public OrderDetailResponseDTO(Long idProduct, Integer quantity, Double price) {
//        this.idProduct = idProduct;
//        this.quantity = quantity;
//        this.unitPrice= price;
//    }
//    public Long getIdProduct() {
//        return idProduct;
//    }
//
//    public void setIdProduct(Long idProduct) {
//        this.idProduct = idProduct;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//
//    public Double getUnitPrice() {
//        return unitPrice;
//    }
//
//    public void setUnitPrice(Double unitPrice) {
//        this.unitPrice = unitPrice;
//    }
}
