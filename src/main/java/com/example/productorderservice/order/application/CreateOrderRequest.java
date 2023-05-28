package com.example.productorderservice.order.application;

import org.springframework.util.Assert;

public class CreateOrderRequest {
    private final Long productId;
    private final Integer quantity;

    public CreateOrderRequest(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
        Assert.notNull(productId, "상품 Id는 필수입니다");
        Assert.isTrue(quantity > 0, "수량은 0보다 커야 합니다");
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
