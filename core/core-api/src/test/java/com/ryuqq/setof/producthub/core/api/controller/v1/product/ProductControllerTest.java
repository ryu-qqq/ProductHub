package com.ryuqq.setof.producthub.core.api.controller.v1.product;


import com.ryuqq.setof.domain.core.product.command.ProductGroupInsertService;
import com.ryuqq.setof.producthub.core.api.controller.v1.product.request.ProductGroupCommandContextRequestDto;
import com.ryuqq.setof.producthub.data.ProductModuleHelper;
import com.ryuqq.setof.test.api.RestDocsTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

@Tag("restdocs")
@ExtendWith(RestDocumentationExtension.class)
class ProductControllerTest extends RestDocsTest {


    @Mock
    private ProductGroupInsertService productGroupInsertService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = mockController(productController);
    }

    @Test
    @DisplayName("상품 그룹 등록 API")
    void registerProduct() throws Exception {
        // given
        ProductGroupCommandContextRequestDto requestDto = ProductModuleHelper.toProductGroupCommandContextRequestDto();
        when(productGroupInsertService.insert(any())).thenReturn(1L);

        // when
        given()

                .accept(ContentType.JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestDto)
                .post("/api/v1/product/group")
                .then()
                .status(HttpStatus.OK)
                .apply(document("product-group-register", requestPreprocessor(), responsePreprocessor(),
                        requestFields(
                            fieldWithPath("productGroup.brandId").type(JsonFieldType.NUMBER).description("브랜드 ID"),
                            fieldWithPath("productGroup.categoryId").type(JsonFieldType.NUMBER).description("카테고리 ID"),
                            fieldWithPath("productGroup.sellerId").type(JsonFieldType.NUMBER).description("셀러 ID"),
                            fieldWithPath("productGroup.productGroupName").type(JsonFieldType.STRING).description("상품 그룹명"),
                            fieldWithPath("productGroup.styleCode").type(JsonFieldType.STRING).description("스타일 코드"),
                            fieldWithPath("productGroup.productCondition").type(JsonFieldType.STRING).description("상품 상태 (e.g., NEW, USED)"),
                            fieldWithPath("productGroup.managementType").type(JsonFieldType.STRING).description("관리 타입 (e.g., MANUAL, AUTO)"),
                            fieldWithPath("productGroup.optionType").type(JsonFieldType.STRING).description("옵션 타입 (e.g., OPTION_ONE, OPTION_TWO)"),
                            fieldWithPath("productGroup.regularPrice").type(JsonFieldType.NUMBER).description("정상가"),
                            fieldWithPath("productGroup.currentPrice").type(JsonFieldType.NUMBER).description("현재 가격"),
                            fieldWithPath("productGroup.soldOutYn").type(JsonFieldType.BOOLEAN).description("품절 여부"),
                            fieldWithPath("productGroup.displayYn").type(JsonFieldType.BOOLEAN).description("노출 여부"),

                            fieldWithPath("productNotice.material").type(JsonFieldType.STRING).description("제품 소재"),
                            fieldWithPath("productNotice.color").type(JsonFieldType.STRING).description("제품 색상"),
                            fieldWithPath("productNotice.size").type(JsonFieldType.STRING).description("제품 크기"),
                            fieldWithPath("productNotice.maker").type(JsonFieldType.STRING).description("제조사"),
                            fieldWithPath("productNotice.origin").type(JsonFieldType.STRING).description("원산지"),
                            fieldWithPath("productNotice.washingMethod").type(JsonFieldType.STRING).description("세탁 방법"),
                            fieldWithPath("productNotice.yearMonth").type(JsonFieldType.STRING).description("제조 연월"),
                            fieldWithPath("productNotice.assuranceStandard").type(JsonFieldType.STRING).description("품질 보증 기준"),
                            fieldWithPath("productNotice.asPhone").type(JsonFieldType.STRING).description("AS 연락처"),

                            fieldWithPath("productDelivery.deliveryArea").type(JsonFieldType.STRING).description("배송 지역"),
                            fieldWithPath("productDelivery.deliveryFee").type(JsonFieldType.NUMBER).description("배송 비용"),
                            fieldWithPath("productDelivery.deliveryPeriodAverage").type(JsonFieldType.NUMBER).description("평균 배송 기간"),
                            fieldWithPath("productDelivery.returnMethodDomestic").type(JsonFieldType.STRING).description("국내 반품 방법"),
                            fieldWithPath("productDelivery.returnCourierDomestic").type(JsonFieldType.STRING).description("국내 반품 택배사 코드"),
                            fieldWithPath("productDelivery.returnChargeDomestic").type(JsonFieldType.NUMBER).description("국내 반품 비용"),
                            fieldWithPath("productDelivery.returnExchangeAreaDomestic").type(JsonFieldType.STRING).description("반품/교환 가능 지역"),

                            fieldWithPath("productImageList[].productImageType").type(JsonFieldType.STRING).description("상품 이미지 타입 (e.g., MAIN, DETAIL)"),
                            fieldWithPath("productImageList[].imageUrl").type(JsonFieldType.STRING).description("이미지 URL"),

                            fieldWithPath("detailDescription.detailDescription").type(JsonFieldType.STRING).description("상품 상세 설명"),

                            fieldWithPath("productOptions[].soldOutYn").type(JsonFieldType.BOOLEAN).description("상품 품절 여부"),
                            fieldWithPath("productOptions[].displayYn").type(JsonFieldType.BOOLEAN).description("상품 노출 여부"),
                            fieldWithPath("productOptions[].quantity").type(JsonFieldType.NUMBER).description("상품 수량"),
                            fieldWithPath("productOptions[].additionalPrice").type(JsonFieldType.NUMBER).description("추가 가격"),
                            fieldWithPath("productOptions[].options[].optionName").type(JsonFieldType.STRING).description("옵션 이름 (e.g., SIZE, COLOR)"),
                            fieldWithPath("productOptions[].options[].optionValue").type(JsonFieldType.STRING).description("옵션 값 (e.g., M, Blue)")
                        ),


                        responseFields(
                                beneathPath("data"),
                                fieldWithPath("productGroupId").type(JsonFieldType.NUMBER).description("Product group ID")
                        ),
                        responseFields(
                                beneathPath("response"),
                                statusMsg()
                        )
                ));


    }



}