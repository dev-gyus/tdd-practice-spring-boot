package com.example.productorderservice.order;

import com.example.productorderservice.order.application.CreateOrderRequest;
import com.example.productorderservice.order.application.OrderService;
import com.example.productorderservice.product.application.ProductService;
import com.example.productorderservice.product.ProductSteps;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

//    @BeforeEach
//    void setUp() {
//        InMemoryOrderRepository inMemoryOrderRepository = new InMemoryOrderRepository();
//        OrderPort orderPort = new OrderPort() {
//            @Override
//            public void save(Order order) {
//                inMemoryOrderRepository.save(order);
//            }
//
//            @Override
//            public Product getProductById(Long productId) {
//                return new Product("상품명", 1000, DiscountPolicy.NONE);
//            }
//        };
//        this.orderService = new OrderService(orderPort);
//    }

    @Test
    void 상품주문() {
        productService.addProduct(ProductSteps.상품등록요청_생성());
        final CreateOrderRequest request = OrderSteps.상품주문요청_생성();
        orderService.createOrder(request);
    }

}
