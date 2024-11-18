package com.ryuqq.setof.api.core.data;

import com.ryuqq.setof.api.core.controller.v1.site.request.*;
import com.ryuqq.setof.api.core.controller.v1.site.response.*;
import com.ryuqq.setof.enums.core.*;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SiteModuleHelper {


    public static SiteInsertRequestDto toSiteInsertRequestDto(){
        return new SiteInsertRequestDto("SETOF", "www.set-of.net",
                Origin.KR, SiteType.CRAWL);
    }

    public static CrawlSiteProfileRequestDto toCrawlSiteProfileRequestDto(){
        return new CrawlSiteProfileRequestDto(toCrawlSettingRequestDto(), toCrawlAuthSettingRequestDto(), toCrawlEndpointRequestDtos());
    }

    public static CrawlAuthSettingRequestDto toCrawlAuthSettingRequestDto(){
        return new CrawlAuthSettingRequestDto(
                AuthType.COOKIE,
                "www.set-of.net",
                "Authorization",
                "{\"Authorization\": \"Bearer some_token_value\"}"
        );
    }

    public static CrawlSettingRequestDto toCrawlSettingRequestDto(){
        return new CrawlSettingRequestDto(10, CrawlType.BEAUTIFUL_SOUP);
    }

    public static List<CrawlEndpointRequestDto> toCrawlEndpointRequestDtos(){
        return List.of(new CrawlEndpointRequestDto("/api/v1/product", "",toCrawlTaskRequestDtos()));
    }

    public static List<CrawlTaskRequestDto> toCrawlTaskRequestDtos(){
        return List.of(new CrawlTaskRequestDto(1, ProcessType.PROCESSING, "", ActionType.SAVE_S3, "{}", "","{\"brands\": \"$.english.*[*].{'brandNo': brandNo, 'brandNameEng': brandNameEng, 'brandNameKor': brandNameKor}\"}"));
    }

    public static SiteResponse toSiteResponse(){
        return new SiteResponse(1L,
                "SETOF", "www.set-of.net",
                Origin.KR, SiteType.CRAWL
        );
    }


    public static SiteContextResponse toSiteContextResponse(){
        return new SiteContextResponse(1L,
                "SETOF", "www.set-of.net",
                Origin.KR, SiteType.CRAWL, toCrawlSiteProfileResponse()
        );
    }

    public static List<SiteProfileResponse> toCrawlSiteProfileResponse(){
        return List.of(new CrawlSiteProfileResponse(
        1L,
                toCrawlSettingResponse(),
                toCrawlAuthSettingResponse(),
                toCrawlEndpointResponses(),
                toHeaders()
        ));
    }


    public static CrawlSettingResponse toCrawlSettingResponse(){
        return new CrawlSettingResponse(10, CrawlType.BEAUTIFUL_SOUP);
    }

    public static CrawlAuthSettingResponse toCrawlAuthSettingResponse(){
        return new CrawlAuthSettingResponse(AuthType.TOKEN, "www.set-of.net", "Authorization", "{\"Authorization\": \"Bearer some_token_value\"}");
    }

    public static List<CrawlEndpointResponse> toCrawlEndpointResponses(){
        return List.of(new CrawlEndpointResponse(1L, "/api/v1/product", "",toCrawlTaskResponses()));
    }

    public static List<CrawlTaskResponse> toCrawlTaskResponses(){
        return List.of(new CrawlTaskResponse(1L, 1, ProcessType.PROCESSING, "", ActionType.SAVE_S3, "", "","{\"brands\": \"$.english.*[*].{'brandNo': brandNo, 'brandNameEng': brandNameEng, 'brandNameKor': brandNameKor}\"}"));
    }

    public static Map<String, String> toHeaders(){
        Map<String, String> map = new HashMap<>();
        map.put("Accept", "text/html,application/xhtml+xml");

        return map;
    }

    public static List<CrawlProductInsertRequestDto> toCrawlProductInsertRequestDto(){
        return List.of(new CrawlProductInsertRequestDto(1L, SiteName.MUSTIT, 11111L, "테스트 상품"));
    }


    public static List<CrawlProductResponse> toCrawlProductResponse(){
        return List.of(new CrawlProductResponse(1L, 1L, SiteName.MUSTIT, "1", "테스트 상품",1L));
    }



}
