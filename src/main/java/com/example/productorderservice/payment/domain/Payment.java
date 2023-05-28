package com.example.productorderservice.payment.domain;

import com.example.productorderservice.order.domain.Order;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@Table(name = "payment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(fetch = FetchType.LAZY)
    private Order order;
    private String cardNumber;

    public Payment(Order order, String cardNumber) {
        Assert.notNull(order, "주문 데이터는 필수 입니다.");
        Assert.hasText(cardNumber, "카드 번호는 필수 입니다.");
        this.order = order;
        this.cardNumber = cardNumber;
    }

    public void assignId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public Order getOrder() {return this.order;};

    public String getCardNumber() {
        return cardNumber;
    }
}
