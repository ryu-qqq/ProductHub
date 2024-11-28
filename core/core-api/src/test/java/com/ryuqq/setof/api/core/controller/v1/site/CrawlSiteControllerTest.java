package com.ryuqq.setof.api.core.controller.v1.site;

import com.ryuqq.setof.api.core.controller.v1.site.request.CrawlProductInsertRequestDto;
import com.ryuqq.setof.api.core.controller.v1.site.response.CrawlProductResponse;
import com.ryuqq.setof.api.core.controller.v1.site.service.CrawlProductQueryFacade;
import com.ryuqq.setof.api.core.data.SiteModuleHelper;
import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.generic.SliceUtils;
import com.ryuqq.setof.domain.core.site.crawl.CrawlProductCommandService;
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
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.restdocs.snippet.Attributes.key;

@Tag("restdocs")
@ExtendWith(RestDocumentationExtension.class)
class CrawlSiteControllerTest  extends RestDocsTest {

    @Mock
    private CrawlProductCommandService crawlProductCommandService;

    @Mock
    private CrawlProductQueryFacade crawlProductQueryFacade;


    @InjectMocks
    private CrawlSiteController crawlSiteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = mockController(crawlSiteController);
    }

    @Test
    @DisplayName("Crawl Products 등록  API")
    void registerCrawlProducts() {
        // given
        List<CrawlProductInsertRequestDto> crawlProductInsertRequestDto = SiteModuleHelper.toCrawlProductInsertRequestDto();

        doNothing().when(crawlProductCommandService).inserts(any());

        // when
        given()
                .accept(ContentType.JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(crawlProductInsertRequestDto) // 요청 Body 설정
                .post("/api/v1/site/crawl/product")
                .then()
                .status(HttpStatus.OK)
                .apply(document("crawl-products-inserts", requestPreprocessor(), responsePreprocessor(),
                        requestFields(
                                fieldWithPath("[].siteId").type(JsonFieldType.NUMBER).description("크롤링 사이트 ID"),
                                fieldWithPath("[].siteName").type(JsonFieldType.STRING).description("크롤링 사이트 명"),
                                fieldWithPath("[].siteProductId").type(JsonFieldType.NUMBER).description("크롤링 사이트 ID"),
                                fieldWithPath("[].productName").type(JsonFieldType.STRING).description("크롤링 상품 명")
                        ),
                        responseFields(
                                beneathPath("response"),
                                statusMsg()
                        )
                ));

        verify(crawlProductCommandService).inserts(any());
    }


    @Test
    @DisplayName("Crawl Product 리스트 조회 API")
    void getCrawlProducts() {
        // given
        List<CrawlProductResponse> results = SiteModuleHelper.toCrawlProductResponse();

        Slice<CrawlProductResponse> slice = SliceUtils.toSlice(results, 20, 1);
        slice.setCursor(1L);
        when(crawlProductQueryFacade.getCrawlProducts(any())).thenReturn(slice);

        // when
        given()
                .accept(ContentType.JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("isProductGroupIdNull", false)
                .get("/api/v1/site/crawl/product")
                .then()
                .status(HttpStatus.OK)
                .apply(document("crawl-products-get", requestPreprocessor(), responsePreprocessor(),
                        queryParameters(
                                parameterWithName("isProductGroupIdNull").description("Product Group Id null 여부"),
                                parameterWithName("pageSize").optional().description("페이지 크기 (기본값: 20)").attributes(key("default").value("20")),
                                parameterWithName("cursorId").optional().description("커서 ID (페이징에 사용)")
                        ),
                        responseFields(
                                beneathPath("data").withSubsectionId("siteResponse"),
                                fieldWithPath("content[].crawlProductId").type(JsonFieldType.NUMBER).description("크롤링 프로덕트 ID"),
                                fieldWithPath("content[].siteId").type(JsonFieldType.NUMBER).description("사이트 ID"),
                                fieldWithPath("content[].siteName").type(JsonFieldType.STRING).description("사이트 명"),
                                fieldWithPath("content[].siteProductId").type(JsonFieldType.STRING).description("크롤링 사이트 상품 ID"),
                                fieldWithPath("content[].productName").type(JsonFieldType.STRING).description("크롤링 상품 명"),
                                fieldWithPath("content[].productGroupId").type(JsonFieldType.NUMBER).description("상품 그룹 ID"),

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