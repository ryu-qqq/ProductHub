package com.ryuqq.setof.producthub.core.api.controller.v1.site;

import com.ryuqq.setof.domain.core.site.command.SiteContextCommandFacade;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.request.SiteInsertRequestDto;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.response.SiteContextResponse;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

@Tag("restdocs")
@ExtendWith(RestDocumentationExtension.class)
class SiteControllerTest extends RestDocsTest {

    @Mock
    private SiteQueryFacade siteQueryFacade;

    @Mock
    private SiteContextCommandFacade siteContextCommandFacade;

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
                                fieldWithPath("siteType").type(JsonFieldType.STRING).description("사이트 유형 (e.g., CRAWL, SYNC ..)"),
                                fieldWithPath("siteProfile.siteType").type(JsonFieldType.STRING).description("사이트 유형 (e.g., CRAWL)"),
                                fieldWithPath("siteProfile.crawlSetting.crawlFrequency").type(JsonFieldType.NUMBER).description("크롤링 주기"),
                                fieldWithPath("siteProfile.crawlSetting.crawlType").type(JsonFieldType.STRING).description("크롤링 타입 (e.g., BEAUTIFUL_SOUP)"),
                                fieldWithPath("siteProfile.crawlAuthSetting.authType").type(JsonFieldType.STRING).description("인증 타입 (e.g., TOKEN)"),
                                fieldWithPath("siteProfile.crawlAuthSetting.authEndpoint").type(JsonFieldType.STRING).description("인증 URL (e.g., TOKEN)"),
                                fieldWithPath("siteProfile.crawlAuthSetting.authHeaders").type(JsonFieldType.STRING).description("인증 헤더 (e.g., Authorization)"),
                                fieldWithPath("siteProfile.crawlAuthSetting.authPayload").type(JsonFieldType.STRING).description("헤더 페이도드 (e.g., Authorization)"),

                                fieldWithPath("siteProfile.crawlEndpoints[].endPointUrl").type(JsonFieldType.STRING).description("엔드포인트 URL (e.g., /api/v1/product)"),
                                fieldWithPath("siteProfile.crawlEndpoints[].crawlTasks[].stepOrder").type(JsonFieldType.NUMBER).description("작업 순서"),
                                fieldWithPath("siteProfile.crawlEndpoints[].crawlTasks[].taskType").type(JsonFieldType.STRING).description("작업 종류"),
                                fieldWithPath("siteProfile.crawlEndpoints[].crawlTasks[].actionTarget").type(JsonFieldType.STRING).description("타깃"),
                                fieldWithPath("siteProfile.crawlEndpoints[].crawlTasks[].actionType").type(JsonFieldType.STRING).description("행동 타입"),
                                fieldWithPath("siteProfile.crawlEndpoints[].crawlTasks[].params").type(JsonFieldType.STRING).description("필요 파라미터"),
                                fieldWithPath("siteProfile.crawlEndpoints[].crawlTasks[].responseMapping").type(JsonFieldType.STRING).description("추출 리스폰스")

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

                                fieldWithPath("siteProfile.crawlSetting.crawlFrequency").type(JsonFieldType.NUMBER).description("크롤링 주기"),
                                fieldWithPath("siteProfile.crawlSetting.crawlType").type(JsonFieldType.STRING).description("크롤링 타입 (e.g., BEAUTIFUL_SOUP)"),

                                fieldWithPath("siteProfile.crawlAuthSetting.authType").type(JsonFieldType.STRING).description("인증 타입 (e.g., TOKEN)"),
                                fieldWithPath("siteProfile.crawlAuthSetting.authEndpoint").type(JsonFieldType.STRING).description("인증 URL (e.g., www.set-of.net)"),
                                fieldWithPath("siteProfile.crawlAuthSetting.authHeaders").type(JsonFieldType.STRING).description("헤더 키 (e.g., Authorization)"),
                                fieldWithPath("siteProfile.crawlAuthSetting.authPayload").type(JsonFieldType.STRING).description("토큰 타입 (e.g., Bearer)"),

                                fieldWithPath("siteProfile.crawlEndpoints[].endPointUrl").type(JsonFieldType.STRING).description("엔드포인트 URL (e.g., /api/v1/product)"),
                                fieldWithPath("siteProfile.crawlEndpoints[].crawlTasks[].endpointId").type(JsonFieldType.NUMBER).description("엔드포인트 ID"),
                                fieldWithPath("siteProfile.crawlEndpoints[].crawlTasks[].stepOrder").type(JsonFieldType.NUMBER).description("작업 순서"),
                                fieldWithPath("siteProfile.crawlEndpoints[].crawlTasks[].taskType").type(JsonFieldType.STRING).description("작업 종류"),
                                fieldWithPath("siteProfile.crawlEndpoints[].crawlTasks[].actionTarget").type(JsonFieldType.STRING).description("타깃"),
                                fieldWithPath("siteProfile.crawlEndpoints[].crawlTasks[].actionType").type(JsonFieldType.STRING).description("행동 타입"),
                                fieldWithPath("siteProfile.crawlEndpoints[].crawlTasks[].params").type(JsonFieldType.STRING).description("필요 파라미터"),
                                fieldWithPath("siteProfile.crawlEndpoints[].crawlTasks[].responseMapping").type(JsonFieldType.STRING).description("추출 리스폰스")

                        ),

                        responseFields(
                                beneathPath("response"),
                                statusMsg()
                        )
                ));


    }



}