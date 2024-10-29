package com.ryuqq.setof.producthub.core.api.controller.v1.product;


import com.ryuqq.setof.core.CategoryType;
import com.ryuqq.setof.core.TargetGroup;
import com.ryuqq.setof.domain.core.product.command.ProductGroupInsertFacade;
import com.ryuqq.setof.domain.core.product.query.ProductGroupDomainQueryFacade;
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
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.restdocs.snippet.Attributes.key;

@Tag("restdocs")
@ExtendWith(RestDocumentationExtension.class)
class ProductControllerTest extends RestDocsTest {


    @Mock
    private ProductGroupInsertFacade productGroupInsertFacade;

    @Mock
    private ProductGroupDomainQueryFacade productGroupDomainQueryFacade;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = mockController(productController);
    }

    @Test
    @DisplayName("상품 그룹 등록 API")
    void registerProductGroup() throws Exception {
        // given
        ProductGroupCommandContextRequestDto requestDto = ProductModuleHelper.toProductGroupCommandContextRequestDto();
        when(productGroupInsertFacade.insert(any())).thenReturn(1L);



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

    @Test
    @DisplayName("상품 그룹 리스트 조회 API")
    void getProductGroups() throws Exception {
        // given
        when(productGroupDomainQueryFacade.getProductGroupContexts(any())).thenReturn();

        // when
        given()
                .accept(ContentType.JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .get("/api/v1/product/group")
                .then()
                .status(HttpStatus.OK)
                .apply(document("product-group-get", requestPreprocessor(), responsePreprocessor(),
                        queryParameters(
                                parameterWithName("productStatus").optional().description("상품 상태 (판매 중, 품절 등)"),
                                parameterWithName("managementType").optional().description("관리 유형 (예: 직접 관리, 위탁 등)"),
                                parameterWithName("categoryId").optional().description("카테고리 ID"),
                                parameterWithName("productGroupIds").optional().description("조회할 상품 그룹 ID 리스트"),
                                parameterWithName("brandIds").optional().description("조회할 브랜드 ID 리스트"),
                                parameterWithName("colorIds").optional().description("조회할 색상 ID 리스트"),
                                parameterWithName("sellerId").optional().description("판매자 ID"),
                                parameterWithName("cursorId").optional().description("커서 ID (페이징에 사용)"),
                                parameterWithName("styleCode").optional().description("스타일 코드"),
                                parameterWithName("pageSize").optional().description("페이지 크기 (기본값: 20)").attributes(key("default").value("20")),
                                parameterWithName("soldOutYn").optional().description("품절 여부 (true: 품절 포함, false: 미포함)"),
                                parameterWithName("displayYn").optional().description("노출 여부 (true: 노출, false: 미노출)"),
                                parameterWithName("minSalePrice").optional().description("최소 판매 가격 (필터링에 사용)"),
                                parameterWithName("maxSalePrice").optional().description("최대 판매 가격 (필터링에 사용)"),
                                parameterWithName("minDiscountRate").optional().description("최소 할인율 (필터링에 사용)"),
                                parameterWithName("maxDiscountRate").optional().description("최대 할인율 (필터링에 사용)"),
                                parameterWithName("startDate").optional().description("시작 날짜 (yyyy-MM-dd HH:mm:ss)").attributes(key("format").value("yyyy-MM-dd HH:mm:ss")),
                                parameterWithName("endDate").optional().description("종료 날짜 (yyyy-MM-dd HH:mm:ss)").attributes(key("format").value("yyyy-MM-dd HH:mm:ss"))
                        ),
                        responseFields(
                                fieldWithPath("content").description("응답 데이터 리스트").type(JsonFieldType.ARRAY),
                                fieldWithPath("content[].productGroup.productGroupId").type(JsonFieldType.NUMBER).description("상품 그룹 ID"),
                                fieldWithPath("content[].productGroup.sellerId").type(JsonFieldType.NUMBER).description("판매자 ID"),
                                fieldWithPath("content[].productGroup.categories[].id").type(JsonFieldType.NUMBER).description("카테고리 ID"),
                                fieldWithPath("content[].productGroup.categories[].categoryName").type(JsonFieldType.STRING).description("카테고리 이름"),
                                fieldWithPath("content[].productGroup.categories[].depth").type(JsonFieldType.NUMBER).description("카테고리 댑스"),
                                fieldWithPath("content[].productGroup.categories[].parentCategoryId").type(JsonFieldType.NUMBER).description("부모 카테고리 ID"),
                                fieldWithPath("content[].productGroup.categories[].displayYn").type(JsonFieldType.STRING).description("전시 여부"),
                                fieldWithPath("content[].productGroup.categories[].targetGroup").type(JsonFieldType.STRING).description("카테고리 타겟 그룹"),
                                fieldWithPath("content[].productGroup.categories[].categoryType").type(JsonFieldType.STRING).description("카테고리 타입"),
                                fieldWithPath("content[].productGroup.categories[].path").type(JsonFieldType.STRING).description("카테고리 경로"),


                                fieldWithPath("content[].productGroup.brand.id").type(JsonFieldType.NUMBER).description("브랜드 ID"),
                                fieldWithPath("content[].productGroup.brand.name").type(JsonFieldType.STRING).description("브랜드 이름"),
                                fieldWithPath("content[].productGroup.productGroupName").type(JsonFieldType.STRING).description("상품 그룹명"),
                                fieldWithPath("content[].productGroup.styleCode").type(JsonFieldType.STRING).description("스타일 코드"),
                                fieldWithPath("content[].productGroup.productCondition").type(JsonFieldType.STRING).description("상품 상태 (예: 새 상품)"),
                                fieldWithPath("content[].productGroup.managementType").type(JsonFieldType.STRING).description("관리 유형"),
                                fieldWithPath("content[].productGroup.optionType").type(JsonFieldType.STRING).description("옵션 타입"),
                                fieldWithPath("content[].productGroup.price.amount").type(JsonFieldType.NUMBER).description("상품 가격"),
                                fieldWithPath("content[].productGroup.soldOutYn").type(JsonFieldType.BOOLEAN).description("품절 여부 (true: 품절)"),
                                fieldWithPath("content[].productGroup.displayYn").type(JsonFieldType.BOOLEAN).description("노출 여부 (true: 노출)"),
                                fieldWithPath("content[].productGroup.productStatus").type(JsonFieldType.STRING).description("상품 상태"),

                                fieldWithPath("content[].productDelivery.deliveryArea").type(JsonFieldType.STRING).description("배송 지역"),
                                fieldWithPath("content[].productDelivery.deliveryFee").type(JsonFieldType.NUMBER).description("배송비"),
                                fieldWithPath("content[].productDelivery.deliveryPeriodAverage").type(JsonFieldType.NUMBER).description("평균 배송 기간"),
                                fieldWithPath("content[].productDelivery.returnMethodDomestic").type(JsonFieldType.STRING).description("국내 반품 방법"),
                                fieldWithPath("content[].productDelivery.returnCourierDomestic").type(JsonFieldType.STRING).description("국내 반품 택배사 코드"),
                                fieldWithPath("content[].productDelivery.returnChargeDomestic").type(JsonFieldType.NUMBER).description("국내 반품 요금"),
                                fieldWithPath("content[].productDelivery.returnExchangeAreaDomestic").type(JsonFieldType.STRING).description("국내 반품/교환 지역"),

                                fieldWithPath("content[].productNotice.material").type(JsonFieldType.STRING).description("소재"),
                                fieldWithPath("content[].productNotice.color").type(JsonFieldType.STRING).description("색상"),
                                fieldWithPath("content[].productNotice.size").type(JsonFieldType.STRING).description("사이즈"),
                                fieldWithPath("content[].productNotice.maker").type(JsonFieldType.STRING).description("제조사"),
                                fieldWithPath("content[].productNotice.origin").type(JsonFieldType.STRING).description("원산지"),
                                fieldWithPath("content[].productNotice.washingMethod").type(JsonFieldType.STRING).description("세탁 방법"),
                                fieldWithPath("content[].productNotice.yearMonth").type(JsonFieldType.STRING).description("제조 연월"),
                                fieldWithPath("content[].productNotice.assuranceStandard").type(JsonFieldType.STRING).description("품질 보증 기준"),
                                fieldWithPath("content[].productNotice.asPhone").type(JsonFieldType.STRING).description("A/S 전화번호"),

                                fieldWithPath("content[].productGroupImages[].productImageType").type(JsonFieldType.STRING).description("상품 이미지 유형"),
                                fieldWithPath("content[].productGroupImages[].imageUrl").type(JsonFieldType.STRING).description("상품 이미지 URL"),

                                fieldWithPath("content[].productDetailDescription.detailDescription").type(JsonFieldType.STRING).description("상품 상세 설명"),

                                fieldWithPath("content[].product").description("상품 옵션 목록"),
                                fieldWithPath("content[].product[].productGroupId").type(JsonFieldType.NUMBER).description("상품 그룹 ID"),
                                fieldWithPath("content[].product[].productId").type(JsonFieldType.NUMBER).description("상품 ID"),
                                fieldWithPath("content[].product[].quantity").type(JsonFieldType.NUMBER).description("수량"),
                                fieldWithPath("content[].product[].soldOutYn").type(JsonFieldType.BOOLEAN).description("품절 여부"),
                                fieldWithPath("content[].product[].displayYn").type(JsonFieldType.BOOLEAN).description("노출 여부"),
                                fieldWithPath("content[].product[].option").type(JsonFieldType.ARRAY).description("옵션명"),
                                fieldWithPath("content[].product[].options[].optionGroupId").type(JsonFieldType.NUMBER).description("옵션 그룹 ID"),
                                fieldWithPath("content[].product[].options[].optionDetailId").type(JsonFieldType.NUMBER).description("옵션 상세 ID"),
                                fieldWithPath("content[].product[].options[].optionName").type(JsonFieldType.STRING).description("옵션 이름"),
                                fieldWithPath("content[].product[].options[].optionValue").type(JsonFieldType.STRING).description("옵션 값"),
                                fieldWithPath("content[].product[].additionalPrice").type(JsonFieldType.NUMBER).description("추가 가격"),

                                fieldWithPath("last").type(JsonFieldType.BOOLEAN).description("마지막 페이지 여부"),
                                fieldWithPath("first").type(JsonFieldType.BOOLEAN).description("첫 페이지 여부"),
                                fieldWithPath("sort").type(JsonFieldType.STRING).description("정렬 여부"),
                                fieldWithPath("size").type(JsonFieldType.NUMBER).description("페이지 크기"),
                                fieldWithPath("numberOfElements").type(JsonFieldType.NUMBER).description("현재 페이지의 요소 개수"),
                                fieldWithPath("empty").type(JsonFieldType.BOOLEAN).description("페이지 비어있는지 여부"),
                                fieldWithPath("cursor").type(JsonFieldType.NUMBER).description("다음 페이지를 위한 커서"),
                                fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("총 요소 개수")
                        ),
                        responseFields(
                                beneathPath("response"),
                                statusMsg()
                        )
                ));


    }



}