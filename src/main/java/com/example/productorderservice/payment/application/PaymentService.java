package com.example.productorderservice.payment.application;

import com.example.productorderservice.order.domain.Order;
import com.example.productorderservice.payment.application.port.PaymentPort;
import com.example.productorderservice.payment.domain.Payment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentService {
    private final PaymentPort paymentPort;

    PaymentService(PaymentPort paymentPort) {
        this.paymentPort = paymentPort;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> payment(@RequestBody PaymentRequest request) {
        final Order order = paymentPort.getOrder(request.getOrderId());
        final Payment payment = new Payment(order, request.getCardNumber());
        paymentPort.pay(payment);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
