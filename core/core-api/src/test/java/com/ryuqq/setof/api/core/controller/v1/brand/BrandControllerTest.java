package com.ryuqq.setof.api.core.controller.v1.brand;

import com.ryuqq.setof.api.core.controller.v1.brand.response.BrandResponse;
import com.ryuqq.setof.api.core.controller.v1.brand.service.BrandService;
import com.ryuqq.setof.api.core.data.BrandModuleHelper;
import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.generic.SliceUtils;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.restdocs.snippet.Attributes.key;

@Tag("restdocs")
@ExtendWith(RestDocumentationExtension.class)
class BrandControllerTest extends RestDocsTest {

    @Mock
    BrandService brandService;

    @InjectMocks
    private BrandController brandController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = mockController(brandController);
    }


    @Test
    @DisplayName("브랜드 리스트 조회 API")
    void getBrands() throws Exception {
        // given
        BrandResponse brandResponse = BrandModuleHelper.toBrandResponse();

        List<BrandResponse> results = List.of(brandResponse);
        Slice<BrandResponse> slice = SliceUtils.toSlice(results, 20, 1);
        slice.setCursor(1L);

        when(brandService.fetchBrands(any())).thenReturn(slice);

        // when
        given()
                .accept(ContentType.JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .get("/api/v1/brands")
                .then()
                .status(HttpStatus.OK)
                .apply(document("brand-get", requestPreprocessor(), responsePreprocessor(),
                        queryParameters(
                                parameterWithName("brandIds").optional().description("조회할 브랜드 ID 리스트"),
                                parameterWithName("cursorId").optional().description("커서 ID (페이징에 사용)"),
                                parameterWithName("pageSize").optional().description("페이지 크기 (기본값: 20)").attributes(key("default").value("20"))
                        ),
                        responseFields(
                                beneathPath("data").withSubsectionId("BrandResponseContent"),

                                fieldWithPath("content[].brandId").type(JsonFieldType.NUMBER).description("브랜드 ID"),
                                fieldWithPath("content[].brandName").type(JsonFieldType.STRING).description("브랜드 이름"),
                                fieldWithPath("content[].brandNameKr").type(JsonFieldType.STRING).description("브랜드 한국 이름"),
                                fieldWithPath("content[].brandIconImageUrl").type(JsonFieldType.STRING).description("브랜드 이미지 URL"),
                                fieldWithPath("content[].displayYn").type(JsonFieldType.BOOLEAN).description("브랜드 전시 여부"),

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