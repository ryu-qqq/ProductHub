package com.ryuqq.setof.producthub.data;

import com.ryuqq.setof.core.*;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.request.*;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.response.*;

import java.util.List;

public class SiteModuleHelper {


    public static SiteInsertRequestDto toSiteInsertRequestDto(){
        return new SiteInsertRequestDto("SETOF", "www.set-of.net",
                Origin.KR, SiteType.CRAWL, toCrawlSiteProfileRequestDto());
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
        return List.of(new CrawlEndpointRequestDto("/api/v1/product", toCrawlTaskRequestDtos()));
    }

    public static List<CrawlTaskRequestDto> toCrawlTaskRequestDtos(){
        return List.of(new CrawlTaskRequestDto(1, TaskType.API_CALL, "", ActionType.EXTRACT, "{}", "{\"brands\": \"$.english.*[*].{'brandNo': brandNo, 'brandNameEng': brandNameEng, 'brandNameKor': brandNameKor}\"}"));
    }


    public static SiteContextResponse toSiteContextResponse(){
        return new SiteContextResponse(1L,
                "SETOF", "www.set-of.net",
                Origin.KR, SiteType.CRAWL, toCrawlSiteProfileResponse()
        );
    }

    public static CrawlSiteProfileResponse toCrawlSiteProfileResponse(){
        return new CrawlSiteProfileResponse(
                toCrawlSettingResponse(),
                toCrawlAuthSettingResponse(),
                toCrawlEndpointResponses()
        );
    }

    public static CrawlSettingResponse toCrawlSettingResponse(){
        return new CrawlSettingResponse(10, CrawlType.BEAUTIFUL_SOUP);
    }

    public static CrawlAuthSettingResponse toCrawlAuthSettingResponse(){
        return new CrawlAuthSettingResponse(AuthType.TOKEN, "www.set-of.net", "Authorization", "{\"Authorization\": \"Bearer some_token_value\"}");
    }

    public static List<CrawlEndpointResponse> toCrawlEndpointResponses(){
        return List.of(new CrawlEndpointResponse("/api/v1/product", toCrawlTaskResponses()));
    }

    public static List<CrawlTaskResponse> toCrawlTaskResponses(){
        return List.of(new CrawlTaskResponse(1L, 1, TaskType.API_CALL, "", ActionType.EXTRACT, "", "{\"brands\": \"$.english.*[*].{'brandNo': brandNo, 'brandNameEng': brandNameEng, 'brandNameKor': brandNameKor}\"}"));
    }


}
