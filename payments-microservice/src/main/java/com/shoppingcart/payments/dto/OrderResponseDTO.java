package com.shoppingcart.payments.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderResponseDTO {

    private Long id;
    private Long clienteId;
    private Date fecha;
    private Double total;
    private String estado;
    private List<OrderDetailResponseDTO> items;

}

