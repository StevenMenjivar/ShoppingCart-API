package com.shoppingcart.payments.models.service;

import com.shoppingcart.payments.customers.OrderFeignClient;
import com.shoppingcart.payments.dto.OrderResponseDTO;
import com.shoppingcart.payments.models.entity.Payment;
import com.shoppingcart.payments.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderFeignClient orderFeignClient;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, OrderFeignClient orderFeignClient) {
        this.paymentRepository = paymentRepository;
        this.orderFeignClient = orderFeignClient;
    }

    public Payment createPayment(Long orderId, String paymentType) {
        OrderResponseDTO order = orderFeignClient.getOrderById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order ID " + orderId + " does not exist");
        }
        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setPaymentType(paymentType);
        payment.setPaymentStatus("COMPLETADO");
        payment.setCreateAt(new Date());
        return paymentRepository.save(payment);
    }


    public List<Payment> findAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment findPaymentById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Payment dont exist"));
    }
}
