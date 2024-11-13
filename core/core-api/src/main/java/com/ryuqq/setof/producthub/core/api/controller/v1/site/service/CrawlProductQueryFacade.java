package com.ryuqq.setof.producthub.core.api.controller.v1.site.service;

import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.site.CrawlProduct;
import com.ryuqq.setof.domain.core.site.CrawlProductFilter;
import com.ryuqq.setof.domain.core.site.CrawlProductQueryService;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.mapper.CrawlProductSliceMapper;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.response.CrawlProductResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CrawlProductQueryFacade {

    private final CrawlProductQueryService crawlProductQueryService;
    private final CrawlProductSliceMapper crawlProductSliceMapper;

    public CrawlProductQueryFacade(CrawlProductQueryService crawlProductQueryService, CrawlProductSliceMapper crawlProductSliceMapper) {
        this.crawlProductQueryService = crawlProductQueryService;
        this.crawlProductSliceMapper = crawlProductSliceMapper;
    }

    public Slice<CrawlProductResponse> getCrawlProducts(CrawlProductFilter crawlProductFilter){
        List<CrawlProduct> crawlProducts = crawlProductQueryService.getCrawlProducts(crawlProductFilter);
        List<CrawlProductResponse> crawlProductResponses = crawlProducts.stream().map(CrawlProductResponse::toCrawlProductResponse).toList();
        long crawlProductCount = crawlProductQueryService.getCrawlProductCount(crawlProductFilter);
        return crawlProductSliceMapper.toSlice(crawlProductResponses, crawlProductFilter.pageSize(), crawlProductCount);
    }

}
