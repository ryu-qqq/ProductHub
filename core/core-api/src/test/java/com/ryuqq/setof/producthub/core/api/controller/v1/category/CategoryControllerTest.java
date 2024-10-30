package com.ryuqq.setof.producthub.core.api.controller.v1.category;

import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.generic.SliceUtils;
import com.ryuqq.setof.producthub.core.api.controller.v1.category.response.CategoryResponse;
import com.ryuqq.setof.producthub.core.api.controller.v1.category.service.CategoryService;
import com.ryuqq.setof.producthub.data.CategoryModuleHelper;
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
class CategoryControllerTest extends RestDocsTest {

    @Mock
    CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = mockController(categoryController);
    }


    @Test
    @DisplayName("카테고리 리스트 조회 API")
    void getCategories() throws Exception {
        // given
        List<CategoryResponse> categoryResponses = CategoryModuleHelper.toCategoryResponses();

        Slice<CategoryResponse> slice = SliceUtils.toSlice(categoryResponses, 20, 4);
        slice.setCursor(1L);

        when(categoryService.getCategories(any())).thenReturn(slice);

        // when
        given()
                .accept(ContentType.JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .get("/api/v1/categories")
                .then()
                .status(HttpStatus.OK)
                .apply(document("category-get", requestPreprocessor(), responsePreprocessor(),
                        queryParameters(
                                parameterWithName("categoryIds").optional().description("조회할 카테고리 ID 리스트"),
                                parameterWithName("targetGroup").optional().description("조회할 카테고리 그룹"),
                                parameterWithName("categoryType").optional().description("조회할 카테고리 타입"),
                                parameterWithName("cursorId").optional().description("커서 ID (페이징에 사용)"),
                                parameterWithName("pageSize").optional().description("페이지 크기 (기본값: 20)").attributes(key("default").value("20"))
                        ),
                        responseFields(
                                beneathPath("data").withSubsectionId("CategoryResponseContent"),

                                fieldWithPath("content[].categoryId").type(JsonFieldType.NUMBER).description("카테고리 ID"),
                                fieldWithPath("content[].categoryName").type(JsonFieldType.STRING).description("카테고리 이름"),
                                fieldWithPath("content[].depth").type(JsonFieldType.NUMBER).description("카테고리 댑스"),
                                fieldWithPath("content[].parentCategoryId").type(JsonFieldType.NUMBER).description("부모 카테고리 ID"),
                                fieldWithPath("content[].displayYn").type(JsonFieldType.BOOLEAN).description("전시 여부"),
                                fieldWithPath("content[].targetGroup").type(JsonFieldType.STRING).description("카테고리 타겟 그룹"),
                                fieldWithPath("content[].categoryType").type(JsonFieldType.STRING).description("카테고리 타입"),
                                fieldWithPath("content[].path").type(JsonFieldType.STRING).description("카테고리 경로"),

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

    @Test
    @DisplayName("하위 카테고리 리스트 조회 API")
    void getChildCategories() throws Exception {
        // given
        List<CategoryResponse> categoryResponses = CategoryModuleHelper.toCategoryResponses();
        when(categoryService.getChildCategories(anyLong())).thenReturn(categoryResponses);

        // when
        given()
                .accept(ContentType.JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .get("/api/v1/categories/{categoryId}/children", "1")
                .then()
                .status(HttpStatus.OK)
                .apply(document("category-child-get", requestPreprocessor(), responsePreprocessor(),
                        pathParameters(
                                parameterWithName("categoryId").optional().description("조회할 카테고리 ID")
                        ),
                        responseFields(
                                beneathPath("data[]").withSubsectionId("CategoryResponseList"),

                                fieldWithPath("categoryId").type(JsonFieldType.NUMBER).description("카테고리 ID"),
                                fieldWithPath("categoryName").type(JsonFieldType.STRING).description("카테고리 이름"),
                                fieldWithPath("depth").type(JsonFieldType.NUMBER).description("카테고리 댑스"),
                                fieldWithPath("parentCategoryId").type(JsonFieldType.NUMBER).description("부모 카테고리 ID"),
                                fieldWithPath("displayYn").type(JsonFieldType.BOOLEAN).description("전시 여부"),
                                fieldWithPath("targetGroup").type(JsonFieldType.STRING).description("카테고리 타겟 그룹"),
                                fieldWithPath("categoryType").type(JsonFieldType.STRING).description("카테고리 타입"),
                                fieldWithPath("path").type(JsonFieldType.STRING).description("카테고리 경로")
                        ),

                        responseFields(
                                beneathPath("response"),
                                statusMsg()
                        )
                ));


    }

    @Test
    @DisplayName("상위 카테고리 리스트 조회 API")
    void getParentCategories() throws Exception {
        // given
        List<CategoryResponse> categoryResponses = CategoryModuleHelper.toCategoryResponses();
        when(categoryService.getParentCategories(anyLong())).thenReturn(categoryResponses);

        // when
        given()
                .accept(ContentType.JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .get("/api/v1/categories/{categoryId}/parents", "1")
                .then()
                .status(HttpStatus.OK)
                .apply(document("category-parent-get", requestPreprocessor(), responsePreprocessor(),
                        pathParameters(
                                parameterWithName("categoryId").optional().description("조회할 카테고리 ID")
                        ),
                        responseFields(
                                beneathPath("data[]").withSubsectionId("CategoryResponseList"),

                                fieldWithPath("categoryId").type(JsonFieldType.NUMBER).description("카테고리 ID"),
                                fieldWithPath("categoryName").type(JsonFieldType.STRING).description("카테고리 이름"),
                                fieldWithPath("depth").type(JsonFieldType.NUMBER).description("카테고리 댑스"),
                                fieldWithPath("parentCategoryId").type(JsonFieldType.NUMBER).description("부모 카테고리 ID"),
                                fieldWithPath("displayYn").type(JsonFieldType.BOOLEAN).description("전시 여부"),
                                fieldWithPath("targetGroup").type(JsonFieldType.STRING).description("카테고리 타겟 그룹"),
                                fieldWithPath("categoryType").type(JsonFieldType.STRING).description("카테고리 타입"),
                                fieldWithPath("path").type(JsonFieldType.STRING).description("카테고리 경로")

                        ),

                        responseFields(
                                beneathPath("response"),
                                statusMsg()
                        )
                ));


    }

}