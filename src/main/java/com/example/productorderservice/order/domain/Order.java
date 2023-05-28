package com.example.productorderservice.order.domain;

import com.example.productorderservice.product.domain.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    private Product product;
    private Integer quantity;

    public Order(Product product, Integer quantity) {
        Assert.notNull(product, "상품은 필수입니다.");
        Assert.isTrue(quantity > 0, "개수는 0 보다 커야 합니다.");
        this.product = product;
        this.quantity = quantity;
    }

    public void assignId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public int getTotalPrice() {
        return product.getDiscountedPrice() * this.quantity;
    }
}
