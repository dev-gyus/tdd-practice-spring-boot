package com.example.productorderservice.product.application;

import com.example.productorderservice.product.domain.DiscountPolicy;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommandProductResponse {
    private Long id;
    private String name;
    private int price;
    private DiscountPolicy discountPolicy;
}
