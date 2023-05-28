package com.example.productorderservice.order;

import com.example.productorderservice.order.domain.Order;
import com.example.productorderservice.product.domain.DiscountPolicy;
import com.example.productorderservice.product.domain.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderTest {

    @Test
    void getTotalPrice() {
        // given
        Order order = new Order(new Product("상품명", 1000, DiscountPolicy.NONE), 2);
        // when
        int totalPrice = order.getTotalPrice();
        // then
        Assertions.assertThat(totalPrice).isEqualTo(2000);
    }
}
