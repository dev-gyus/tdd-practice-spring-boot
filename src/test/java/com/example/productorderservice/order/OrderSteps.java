package com.example.productorderservice.order;

import com.example.productorderservice.order.application.CreateOrderRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class OrderSteps {
    public static CreateOrderRequest 상품주문요청_생성() {
        Long productId = 1L;
        Integer quantity = 1;
        CreateOrderRequest request = new CreateOrderRequest(productId, quantity);
        return request;
    }

    public static ExtractableResponse<Response> 상품주문_요청(CreateOrderRequest request) {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .post("/orders")
                .then()
                .log().all().extract();
        return response;
    }
}
