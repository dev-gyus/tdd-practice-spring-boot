package com.example.productorderservice.product;

import com.example.productorderservice.product.application.AddProductRequest;
import com.example.productorderservice.product.application.UpdateProductRequest;
import com.example.productorderservice.product.domain.DiscountPolicy;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.apache.http.entity.ContentType;
import org.springframework.http.MediaType;

public class ProductSteps {
    public static ExtractableResponse<Response> 상품등록_요청(AddProductRequest request) {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(ContentType.APPLICATION_JSON.getMimeType())
                .body(request)
                .when()
                .post("/products")
                .then()
                .log().all().extract();
        return response;
    }

    public static AddProductRequest 상품등록요청_생성() {
        final String name = "상품명";
        final int price = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        final AddProductRequest request = new AddProductRequest(name, price, discountPolicy);
        return request;
    }

    public static ExtractableResponse<Response> 상품조회요청(Long productId) {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when()
                .get("/products/{productId}", productId)
                .then().log().all()
                .extract();
        return response;
    }
    static ExtractableResponse<Response> 상품수정요청(Long productId, UpdateProductRequest request) {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .put("/products/{productId}", productId)
                .then()
                .log().all().extract();
        return response;
    }
}
