package com.shoppingcart.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponseDTO {
    private Long id;
    private Long clienteId;
    private Date fecha;
    private Double total;
    private String estado;
    private List<OrderDetailResponseDTO> detalleOrden;
//    public OrderResponseDTO(Long id, Long clienteId, Date fecha, Double total, String estado, List<OrderDetailResponseDTO> detalleOrden) {
//        this.id = id;
//        this.clienteId = clienteId;
//        this.fecha = fecha;
//        this.total = total;
//        this.estado = estado;
//        this.detalleOrden = detalleOrden;
//    }
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getClienteId() {
//        return clienteId;
//    }
//
//    public void setClienteId(Long clienteId) {
//        this.clienteId = clienteId;
//    }
//
//    public Date getFecha() {
//        return fecha;
//    }
//
//    public void setFecha(Date fecha) {
//        this.fecha = fecha;
//    }
//
//    public Double getTotal() {
//        return total;
//    }
//
//    public void setTotal(Double total) {
//        this.total = total;
//    }
//
//    public String getEstado() {
//        return estado;
//    }
//
//    public void setEstado(String estado) {
//        this.estado = estado;
//    }
//
//    public List<OrderDetailResponseDTO> getDetalleOrden() {
//        return detalleOrden;
//    }
//
//    public void setDetalleOrden(List<OrderDetailResponseDTO> detalleOrden) {
//        this.detalleOrden = detalleOrden;
//    }
    //    private List<OrderDetail> orders;
//    private Double totalOrder;
//
//    public OrderResponseDTO(List<OrderDetail> orders, Double totalOrder) {
//        this.orders = orders;
//        this.totalOrder = totalOrder;
//    }
//
//    public List<OrderDetail> getItems() {
//        return orders;
//    }
//
//    public void setItems(List<OrderDetail> orders) {
//        this.orders = orders;
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
