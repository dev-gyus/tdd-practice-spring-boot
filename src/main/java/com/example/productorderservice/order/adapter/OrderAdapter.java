package com.example.productorderservice.order.adapter;

import com.example.productorderservice.order.application.port.OrderPort;
import com.example.productorderservice.order.domain.Order;
import com.example.productorderservice.product.domain.Product;
import com.example.productorderservice.product.adapter.ProductRepository;
import org.springframework.stereotype.Component;

@Component
class OrderAdapter implements OrderPort {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderAdapter(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
    }
}
