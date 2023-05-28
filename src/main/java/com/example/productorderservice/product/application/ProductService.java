package com.example.productorderservice.product.application;

import com.example.productorderservice.product.application.port.ProductPort;
import com.example.productorderservice.product.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public
class ProductService {
    private final ProductPort productPort;

    @Autowired
    public ProductService(ProductPort productPort) {
        this.productPort = productPort;
    }

    @PostMapping
    public ResponseEntity<CommandProductResponse> addProduct(@RequestBody final AddProductRequest request) {
        final Product product = new Product(request.name(), request.price(), request.discountPolicy());
        productPort.save(product);
        return new ResponseEntity<>(
                CommandProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .discountPolicy(product.getDiscountPolicy())
                        .build(),
                HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<GetProductResponse> getProduct(@PathVariable Long productId) {
        final Product product = productPort.getProduct(productId);
        return new ResponseEntity<>(new GetProductResponse(
                product.getId(),
                product.getName(),
                product.getDiscountedPrice(),
                product.getDiscountPolicy()
        ), HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    @Transactional
    public ResponseEntity<CommandProductResponse> updateProduct(@PathVariable Long productId, @RequestBody UpdateProductRequest request) {
        Product product = productPort.getProduct(productId);
        product.update(request.name(), request.price(), request.policy());
        return new ResponseEntity<>(
                CommandProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .discountPolicy(product.getDiscountPolicy())
                        .build(),
                HttpStatus.ACCEPTED);
    }
}
