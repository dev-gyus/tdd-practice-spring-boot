package com.example.productorderservice.product;

import com.example.productorderservice.product.domain.DiscountPolicy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountPolicyTest {
    @Test
    void nonDiscountPolicy() {
        final int price = 1000;
        final int discountedPrice = DiscountPolicy.NONE.applyDiscount(price);
        assertThat(discountedPrice).isEqualTo(price);
    }

    @Test
    void fix1000DiscountPolicy() {
        final int price = 1000;
        final int discountedPrice = DiscountPolicy.FIX_1000_AMOUNT.applyDiscount(price);
        assertThat(discountedPrice).isEqualTo(price - 1000);
    }

}