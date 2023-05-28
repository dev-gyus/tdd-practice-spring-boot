package com.example.productorderservice.payment.application;

import org.springframework.util.Assert;

public class PaymentRequest {
    private final Long orderId;
    private final String cardNumber;

    public PaymentRequest(Long orderId, String cardNumber) {
        Assert.notNull(orderId, "주문 번호는 필수 입니다.");
        Assert.hasText(cardNumber, "카드 번호는 필수입니다.");
        this.orderId = orderId;
        this.cardNumber = cardNumber;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getCardNumber() {
        return cardNumber;
    }
}
