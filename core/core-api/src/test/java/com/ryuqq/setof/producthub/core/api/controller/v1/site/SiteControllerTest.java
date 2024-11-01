package com.ryuqq.setof.producthub.core.api.controller.v1.site;

import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.generic.SliceUtils;
import com.ryuqq.setof.domain.core.site.command.SiteContextCommandFacade;
import com.ryuqq.setof.domain.core.site.command.SiteProfileCommandFacade;
import com.ryuqq.setof.producthub.core.api.controller.v1.product.response.ProductGroupContextResponse;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.request.CrawlSiteProfileRequestDto;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.request.SiteInsertRequestDto;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.response.SiteContextResponse;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.response.SiteResponse;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.service.SiteQueryFacade;
import com.ryuqq.setof.producthub.data.SiteModuleHelper;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;

@Tag("restdocs")
@ExtendWith(RestDocumentationExtension.class)
class SiteControllerTest extends RestDocsTest {

    @Mock
    private SiteQueryFacade siteQueryFacade;

    @Mock
    private SiteContextCommandFacade siteContextCommandFacade;

    @Mock
    private SiteProfileCommandFacade siteProfileCommandFacade;

    @InjectMocks
    private SiteController siteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = mockController(siteController);
    }

    @Test
    @DisplayName("사이트 등록 API")
    void registerSite() throws Exception {
        // given
        SiteInsertRequestDto requestDto = SiteModuleHelper.toSiteInsertRequestDto();
        when(siteContextCommandFacade.insert(any())).thenReturn(1L);

        // when
        given()
                .accept(ContentType.JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestDto)
                .post("/api/v1/site")
                .then()
                .status(HttpStatus.OK)
                .apply(document("site-register", requestPreprocessor(), responsePreprocessor(),
                        requestFields(
                                fieldWithPath("name").type(JsonFieldType.STRING).description("사이트 이름 (e.g., SETOF)"),
                                fieldWithPath("baseUrl").type(JsonFieldType.STRING).description("사이트 URL (e.g., www.set-of.net)"),
                                fieldWithPath("countryCode").type(JsonFieldType.STRING).description("사이트 국가 (e.g., KR)"),
                                fieldWithPath("siteType").type(JsonFieldType.STRING).description("사이트 유형 (e.g., CRAWL, SYNC ..)")
                        ),
                        responseFields(
                                beneathPath("data"),
                                fieldWithPath("siteId").type(JsonFieldType.NUMBER).description("Site ID")
                        ),
                        responseFields(
                                beneathPath("response"),
                                statusMsg()
                        )
                ));
    }

    @Test
    @DisplayName("사이트 프로필 등록 API")
    void registerSiteProfile() throws Exception {
        // given
        CrawlSiteProfileRequestDto requestDto = SiteModuleHelper.toCrawlSiteProfileRequestDto();
        when(siteProfileCommandFacade.insert(any(), anyLong(), any())).thenReturn(1L);

        // when
        given()
                .accept(ContentType.JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestDto)
                .post("/api/v1/site/{siteType}/{siteId}", "CRAWL", "1")
                .then()
                .status(HttpStatus.OK)
                .apply(document("site-profile-register", requestPreprocessor(), responsePreprocessor(),
                        pathParameters(
                                parameterWithName("siteType").description("Site Type"),
                                parameterWithName("siteId").description("Site ID")
                        ),
                        requestFields(
                                fieldWithPath("siteType").type(JsonFieldType.STRING).description("사이트 유형 (e.g., CRAWL)"),
                                fieldWithPath("crawlSetting.crawlFrequency").type(JsonFieldType.NUMBER).description("크롤링 주기"),
                                fieldWithPath("crawlSetting.crawlType").type(JsonFieldType.STRING).description("크롤링 타입 (e.g., BEAUTIFUL_SOUP)"),
                                fieldWithPath("crawlAuthSetting.authType").type(JsonFieldType.STRING).description("인증 타입 (e.g., TOKEN)"),
                                fieldWithPath("crawlAuthSetting.authEndpoint").type(JsonFieldType.STRING).description("인증 URL (e.g., TOKEN)"),
                                fieldWithPath("crawlAuthSetting.authHeaders").type(JsonFieldType.STRING).description("인증 헤더 (e.g., Authorization)"),
                                fieldWithPath("crawlAuthSetting.authPayload").type(JsonFieldType.STRING).description("헤더 페이도드 (e.g., Authorization)"),
                                fieldWithPath("crawlEndpoints[].endPointUrl").type(JsonFieldType.STRING).description("엔드포인트 URL (e.g., /api/v1/product)"),
                                fieldWithPath("crawlEndpoints[].parameters").type(JsonFieldType.STRING).description("엔드포인트 파라미터"),
                                fieldWithPath("crawlEndpoints[].crawlTasks[].stepOrder").type(JsonFieldType.NUMBER).description("작업 순서"),
                                fieldWithPath("crawlEndpoints[].crawlTasks[].taskType").type(JsonFieldType.STRING).description("작업 종류"),
                                fieldWithPath("crawlEndpoints[].crawlTasks[].actionTarget").type(JsonFieldType.STRING).description("타깃"),
                                fieldWithPath("crawlEndpoints[].crawlTasks[].actionType").type(JsonFieldType.STRING).description("행동 타입"),
                                fieldWithPath("crawlEndpoints[].crawlTasks[].params").type(JsonFieldType.STRING).description("필요 파라미터"),
                                fieldWithPath("crawlEndpoints[].crawlTasks[].responseMapping").type(JsonFieldType.STRING).description("추출 리스폰스")
                        ),
                        responseFields(
                                beneathPath("data"),
                                fieldWithPath("siteId").type(JsonFieldType.NUMBER).description("Site ID")
                        ),
                        responseFields(
                                beneathPath("response"),
                                statusMsg()
                        )
                ));
    }


    @Test
    @DisplayName("Site 조회 API")
    void getSite() throws Exception {
        // given
        SiteContextResponse response = SiteModuleHelper.toSiteContextResponse();

        when(siteQueryFacade.getSiteContextResponse(anyLong())).thenReturn(response);
        // when
        given()
                .accept(ContentType.JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .get("/api/v1/site/{siteId}", "1")
                .then()
                .status(HttpStatus.OK)
                .apply(document("site-get", requestPreprocessor(), responsePreprocessor(),
                        pathParameters(
                                parameterWithName("siteId").description("Site ID")
                        ),
                        responseFields(
                                beneathPath("data").withSubsectionId("siteContextResponse"),
                                fieldWithPath("siteId").type(JsonFieldType.NUMBER).description("사이트 ID"),
                                fieldWithPath("siteName").type(JsonFieldType.STRING).description("사이트 이름 (e.g., SETOF)"),
                                fieldWithPath("baseUrl").type(JsonFieldType.STRING).description("사이트 URL (e.g., www.set-of.net)"),
                                fieldWithPath("countryCode").type(JsonFieldType.STRING).description("사이트 국가 (e.g., KR)"),
                                fieldWithPath("siteType").type(JsonFieldType.STRING).description("사이트 유형 (e.g., CRAWL)"),

                                fieldWithPath("siteProfiles[].crawlSetting.crawlFrequency").type(JsonFieldType.NUMBER).description("크롤링 주기"),
                                fieldWithPath("siteProfiles[].crawlSetting.crawlType").type(JsonFieldType.STRING).description("크롤링 타입 (e.g., BEAUTIFUL_SOUP)"),
                                fieldWithPath("siteProfiles[].crawlAuthSetting.authType").type(JsonFieldType.STRING).description("인증 타입 (e.g., TOKEN)"),
                                fieldWithPath("siteProfiles[].crawlAuthSetting.authEndpoint").type(JsonFieldType.STRING).description("인증 URL (e.g., www.set-of.net)"),
                                fieldWithPath("siteProfiles[].crawlAuthSetting.authHeaders").type(JsonFieldType.STRING).description("헤더 키 (e.g., Authorization)"),
                                fieldWithPath("siteProfiles[].crawlAuthSetting.authPayload").type(JsonFieldType.STRING).description("토큰 타입 (e.g., Bearer)"),
                                fieldWithPath("siteProfiles[].crawlEndpoints[].endPointUrl").type(JsonFieldType.STRING).description("엔드포인트 URL (e.g., /api/v1/product)"),
                                fieldWithPath("siteProfiles[].crawlEndpoints[].parameters").type(JsonFieldType.STRING).description("엔드포인트 파라미터"),
                                fieldWithPath("siteProfiles[].crawlEndpoints[].crawlTasks[].endpointId").type(JsonFieldType.NUMBER).description("엔드포인트 ID"),
                                fieldWithPath("siteProfiles[].crawlEndpoints[].crawlTasks[].stepOrder").type(JsonFieldType.NUMBER).description("작업 순서"),
                                fieldWithPath("siteProfiles[].crawlEndpoints[].crawlTasks[].taskType").type(JsonFieldType.STRING).description("작업 종류"),
                                fieldWithPath("siteProfiles[].crawlEndpoints[].crawlTasks[].actionTarget").type(JsonFieldType.STRING).description("타깃"),
                                fieldWithPath("siteProfiles[].crawlEndpoints[].crawlTasks[].actionType").type(JsonFieldType.STRING).description("행동 타입"),
                                fieldWithPath("siteProfiles[].crawlEndpoints[].crawlTasks[].params").type(JsonFieldType.STRING).description("필요 파라미터"),
                                fieldWithPath("siteProfiles[].crawlEndpoints[].crawlTasks[].responseMapping").type(JsonFieldType.STRING).description("추출 리스폰스")
                        ),

                        responseFields(
                                beneathPath("response"),
                                statusMsg()
                        )
                ));


    }

    @Test
    @DisplayName("Site 리스트 조회 API")
    void getSites() {
        // given
        SiteResponse results = SiteModuleHelper.toSiteResponse();

        Slice<SiteResponse> slice = SliceUtils.toSlice(List.of(results), 20, 1);
        slice.setCursor(1L);
        when(siteQueryFacade.getSiteResponses(any())).thenReturn(slice);

        // when
        given()
                .accept(ContentType.JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .get("/api/v1/site")
                .then()
                .status(HttpStatus.OK)
                .apply(document("sites-get", requestPreprocessor(), responsePreprocessor(),
                        queryParameters(
                                parameterWithName("siteType").optional().description("Site Type"),
                                parameterWithName("pageSize").optional().description("페이지 크기 (기본값: 20)").attributes(key("default").value("20")),
                                parameterWithName("cursorId").optional().description("커서 ID (페이징에 사용)")
                                ),
                        responseFields(
                                beneathPath("data").withSubsectionId("siteResponse"),
                                fieldWithPath("content[].siteId").type(JsonFieldType.NUMBER).description("사이트 ID"),
                                fieldWithPath("content[].siteName").type(JsonFieldType.STRING).description("사이트 이름 (e.g., SETOF)"),
                                fieldWithPath("content[].baseUrl").type(JsonFieldType.STRING).description("사이트 URL (e.g., www.set-of.net)"),
                                fieldWithPath("content[].countryCode").type(JsonFieldType.STRING).description("사이트 국가 (e.g., KR)"),
                                fieldWithPath("content[].siteType").type(JsonFieldType.STRING).description("사이트 유형 (e.g., CRAWL)"),

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