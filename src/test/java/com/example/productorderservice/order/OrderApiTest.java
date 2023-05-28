package com.example.productorderservice.order;

import com.example.productorderservice.ApiTest;
import com.example.productorderservice.order.application.CreateOrderRequest;
import com.example.productorderservice.product.ProductSteps;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class OrderApiTest extends ApiTest {

    @Test
    void 상품주문() {
        // given
        ProductSteps.상품등록_요청(ProductSteps.상품등록요청_생성());
        final CreateOrderRequest request = OrderSteps.상품주문요청_생성();
        // when
        ExtractableResponse<Response> response = OrderSteps.상품주문_요청(request);
        // then
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

}
