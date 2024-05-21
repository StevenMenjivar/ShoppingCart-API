package com.shoppingcart.payments.dto;

import lombok.Data;


@Data
public class OrderDetailResponseDTO {
    private Long productId;
    private Integer quantity;
    private Double price;
}
