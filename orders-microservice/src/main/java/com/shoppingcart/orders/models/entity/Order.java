package com.shoppingcart.orders.models.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cliente_id")
    private Long clienteId;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private String estado;
    private Double total;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderDetail> orderDetails;

    public Order(List<OrderDetail> orderDetails, Double totalOrder) {
        this.orderDetails = orderDetails;
        this.total = totalOrder;
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setOrder(this);
        }
    }

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
//    public String getEstado() {
//        return estado;
//    }
//
//    public void setEstado(String estado) {
//        this.estado = estado;
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
//    public List<OrderDetail> getItems() {
//        return orderDetails;
//    }
//
//    public void setItems(List<OrderDetail> orderDetails) {
//        this.orderDetails = orderDetails;
//    }
//
//    public Double getTotalOrder() {
//        return total;
//    }
//
//    public void setTotalOrder(Double totalOrder) {
//        this.total = totalOrder;
//    }
}