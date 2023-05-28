package com.example.productorderservice.product.application;

import com.example.productorderservice.product.domain.DiscountPolicy;
import org.springframework.util.Assert;

public record GetProductResponse(
        long id,
        String name,
        int price,
        DiscountPolicy discountPolicy
) {
    public GetProductResponse {
        Assert.notNull(id, "상품 Id는 필수 입니다.");
        Assert.hasText(name, "상품 명은 필수 입니다.");
        Assert.isTrue(price > 0, "상품 가격은 1 이상 이어야 합니다.");
        Assert.notNull(discountPolicy, "할인 정책은 필수 입니다.");
    }
}
