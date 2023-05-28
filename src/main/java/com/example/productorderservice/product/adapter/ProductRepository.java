package com.example.productorderservice.product.adapter;

import com.example.productorderservice.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
//    private final Map<Long, Product> persistence = new HashMap<>();
//    private Long sequence = 0L;
//
//    public void save(final Product product) {
//        product.assignId(++sequence);
//        persistence.put(product.getId(), product);
//    }
}
