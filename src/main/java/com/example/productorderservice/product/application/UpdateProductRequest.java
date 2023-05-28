package com.example.productorderservice.product.application;

import com.example.productorderservice.product.domain.DiscountPolicy;
import org.springframework.util.Assert;

public record UpdateProductRequest(String name, int price, DiscountPolicy policy) {
    public UpdateProductRequest {
        Assert.hasText(name, "상품 명은 필수입니다.");
        Assert.isTrue(price > 0, "상품 가격은 1원 이상이어야 합니다.");
        Assert.notNull(policy, "할인 정책은 필수입니다.");
    }
}
