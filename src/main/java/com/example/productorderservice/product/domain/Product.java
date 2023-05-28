package com.example.productorderservice.product.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    private DiscountPolicy discountPolicy;

    public Product(String name, int price, DiscountPolicy discountPolicy) {
        Assert.hasText(name, "상품명은 필수입니다.");
        Assert.isTrue(price > 0, "상품 가격은 0보다 커야 합니다.");
        Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");
        this.discountPolicy = discountPolicy;
        this.price = price;
        this.name = name;
    }

    public void update(String name, int price, DiscountPolicy policy) {
        Assert.hasText(name, "상품 명은 필수입니다.");
        Assert.isTrue(price > 0, "상품 가격은 1원 이상이어야 합니다.");
        Assert.notNull(policy, "할인 정책은 필수입니다.");
        this.discountPolicy = policy;
        this.price = price;
        this.name = name;
    }

    public int getDiscountedPrice() {
        return this.discountPolicy.applyDiscount(price);
    }
}
