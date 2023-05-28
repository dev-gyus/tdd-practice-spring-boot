package com.example.productorderservice.payment;

import com.example.productorderservice.order.application.OrderService;
import com.example.productorderservice.order.OrderSteps;
import com.example.productorderservice.payment.application.PaymentRequest;
import com.example.productorderservice.payment.application.PaymentService;
import com.example.productorderservice.product.application.ProductService;
import com.example.productorderservice.product.ProductSteps;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PaymentServiceTest {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

//    @BeforeEach
//    void setUp() {
//        this.paymentService = new PaymentService(new PaymentAdapter(new PgPayment(), new InMemoryPaymentRepository()));
//    }

    @Test
    void 상품주문() {
        // given
        productService.addProduct(ProductSteps.상품등록요청_생성());
        orderService.createOrder(OrderSteps.상품주문요청_생성());
        final PaymentRequest paymentRequest = PaymentSteps.주문결제요청_생성();
        // when
        paymentService.payment(paymentRequest);
        // then
    }

}
