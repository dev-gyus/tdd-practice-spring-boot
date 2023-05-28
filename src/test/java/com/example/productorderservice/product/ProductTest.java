package com.example.productorderservice.product;

import com.example.productorderservice.product.domain.DiscountPolicy;
import com.example.productorderservice.product.domain.Product;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    @Test
    void update() {
        // given
        Product product = new Product("상품명", 1000, DiscountPolicy.NONE);
        // when
        product.update("상품 수정", 2000, DiscountPolicy.NONE);
        // then
        assertThat(product.getName()).isEqualTo("상품 수정");
        assertThat(product.getDiscountedPrice()).isEqualTo(2000);
        assertThat(product.getDiscountPolicy()).isEqualTo(DiscountPolicy.NONE);
    }

    @Test
    void none_discounted_product() {
        // given
        Product product = new Product("상품명", 1000, DiscountPolicy.NONE);
        // when
        int discountedPrice = product.getDiscountedPrice();
        // then
        assertThat(discountedPrice).isEqualTo(product.getPrice());
    }

    @Test
    void fix_1000_discounted_product() {
        // given
        Product product = new Product("상품명", 1000, DiscountPolicy.FIX_1000_AMOUNT);
        // when
        int discountedPrice = product.getDiscountedPrice();
        // then
        assertThat(discountedPrice).isEqualTo(0);
    }

    @Test
    void over_discounted_product() {
        // given
        Product product = new Product("상품명", 500, DiscountPolicy.FIX_1000_AMOUNT);
        // when
        int discountedPrice = product.getDiscountedPrice();
        // then
        assertThat(discountedPrice).isEqualTo(0);
    }
}