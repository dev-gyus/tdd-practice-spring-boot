package com.example.productorderservice.payment.adapter;

import com.example.productorderservice.payment.domain.Payment;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryPaymentRepository {
    final Map<Long, Payment> persistence = new HashMap<>();
    long sequence = 0L;

    public void save(final Payment payment) {
        payment.assignId(++sequence);
        persistence.put(payment.getId(), payment);
    }
}
