package com.example.productorderservice.payment;

import com.example.productorderservice.ApiTest;
import com.example.productorderservice.order.OrderSteps;
import com.example.productorderservice.payment.application.PaymentRequest;
import com.example.productorderservice.product.ProductSteps;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class PaymentApiTest extends ApiTest {

    @Test
    void 상품주문() {
        // given
        ProductSteps.상품등록_요청(ProductSteps.상품등록요청_생성());
        OrderSteps.상품주문_요청(OrderSteps.상품주문요청_생성());
        final PaymentRequest request = PaymentSteps.주문결제요청_생성();

        // when
        ExtractableResponse<Response> response = PaymentSteps.주문결제요청(request);
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }


}
