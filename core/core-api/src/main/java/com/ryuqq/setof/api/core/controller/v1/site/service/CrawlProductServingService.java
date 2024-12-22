package com.ryuqq.setof.api.core.controller.v1.site.service;

import com.ryuqq.setof.api.core.controller.v1.site.mapper.CrawlProductSliceMapper;
import com.ryuqq.setof.api.core.controller.v1.site.response.CrawlProductResponse;
import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.site.crawl.CrawlProduct;
import com.ryuqq.setof.domain.core.site.crawl.CrawlProductFilter;
import com.ryuqq.setof.domain.core.site.crawl.CrawlProductQueryService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CrawlProductServingService {

    private final CrawlProductQueryService crawlProductQueryService;
    private final CrawlProductSliceMapper crawlProductSliceMapper;

    public CrawlProductServingService(CrawlProductQueryService crawlProductQueryService, CrawlProductSliceMapper crawlProductSliceMapper) {
        this.crawlProductQueryService = crawlProductQueryService;
        this.crawlProductSliceMapper = crawlProductSliceMapper;
    }

    public Slice<CrawlProductResponse> fetchByFilter(CrawlProductFilter crawlProductFilter){
        List<CrawlProduct> crawlProducts = crawlProductQueryService.fetchByFilter(crawlProductFilter);
        List<CrawlProductResponse> crawlProductResponses = crawlProducts.stream().map(CrawlProductResponse::toCrawlProductResponse).toList();
        long crawlProductCount = crawlProductQueryService.countByFilter(crawlProductFilter);
        return crawlProductSliceMapper.toSlice(crawlProductResponses, crawlProductFilter.pageSize(), crawlProductCount);
    }

}
