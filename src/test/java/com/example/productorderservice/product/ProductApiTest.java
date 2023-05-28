package com.example.productorderservice.product;

import com.example.productorderservice.ApiTest;
import com.example.productorderservice.product.adapter.ProductRepository;
import com.example.productorderservice.product.application.ProductService;
import com.example.productorderservice.product.application.UpdateProductRequest;
import com.example.productorderservice.product.domain.DiscountPolicy;
import com.example.productorderservice.product.adapter.ProductAdapter;
import com.example.productorderservice.product.application.port.ProductPort;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

class ProductApiTest extends ApiTest {
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void init() {
        productRepository.deleteAll();
    }

    /**
     * 행동기반 다이어그램으로 FLOW 정의 -> POJO로 먼저 구현 -> Spring Bean 등록 -> JPA 변경
     * 순차적으로 적용
     */
    @Test
    void 상품등록() {
        // given
        final var request = ProductSteps.상품등록요청_생성();

        // when - API 요청
        final var response = ProductSteps.상품등록_요청(request);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void 상품조회() {
        // 상품등록
        ExtractableResponse<Response> savedResponse = ProductSteps.상품등록_요청(ProductSteps.상품등록요청_생성());
        long savedProductId = savedResponse.jsonPath().getLong("id");
        // 상품을 조회
        ExtractableResponse<Response> readResponse = ProductSteps.상품조회요청(savedProductId);
        // 상품의 응답을 검증
        assertThat(readResponse).isNotNull();
        assertThat(readResponse.jsonPath().getString("name")).isEqualTo("상품명");
    }

    @Test
    void 상품수정() {
        // given
        final UpdateProductRequest request = new UpdateProductRequest("상품 수정", 2000, DiscountPolicy.NONE);
        ExtractableResponse<Response> savedResponse = ProductSteps.상품등록_요청(ProductSteps.상품등록요청_생성());
        long savedId = savedResponse.jsonPath().getLong("id");
        // when
        ExtractableResponse<Response> updatedResponse = ProductSteps.상품수정요청(savedId, request);
        long updatedId = updatedResponse.jsonPath().getLong("id");
        // then
        assertThat(updatedResponse.statusCode()).isEqualTo(HttpStatus.ACCEPTED.value());
        ExtractableResponse<Response> readResponse = ProductSteps.상품조회요청(updatedId);
        assertThat(readResponse.jsonPath().getString("name")).isEqualTo(request.name());
        assertThat(readResponse.jsonPath().getInt("price")).isEqualTo(request.price());
        assertThat(readResponse.jsonPath().getString("discountPolicy")).isEqualTo(request.policy().name());
    }

}
