package com.example.productorderservice.payment;

import com.example.productorderservice.payment.application.PaymentRequest;
import com.example.productorderservice.product.application.UpdateProductRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class PaymentSteps {
    static PaymentRequest 주문결제요청_생성() {
        final Long orderId = 1L;
        final String cardNumber = "1234-5678-1234-5678";
        final PaymentRequest request = new PaymentRequest(orderId, cardNumber);
        return request;
    }

    static ExtractableResponse<Response> 주문결제요청(PaymentRequest request) {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .post("/payments")
                .then().log().all().extract();
        return response;
    }
}