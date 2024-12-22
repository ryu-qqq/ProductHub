package com.ryuqq.setof.domain.core.site.crawl;

import com.ryuqq.setof.db.core.site.crawl.CrawlProductQueryRepository;
import com.ryuqq.setof.db.core.site.crawl.dto.CrawlProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrawlProductFinder implements CrawlProductQueryService {

    private final CrawlProductQueryRepository crawlProductQueryRepository;

    public CrawlProductFinder(CrawlProductQueryRepository crawlProductQueryRepository) {
        this.crawlProductQueryRepository = crawlProductQueryRepository;
    }

    public List<CrawlProduct> fetchByFilter(CrawlProductFilter crawlProductFilter){
        List<CrawlProductDto> crawlProductDtos = crawlProductQueryRepository.fetchByFilter(crawlProductFilter.toCrawlProductStorageDto());
        return crawlProductDtos.stream()
                .map(c ->
                        new CrawlProduct(c.getCrawlProductId(), c.getSiteId(), c.getSiteName(),
                                c.getSiteProductId(), c.getProductName(), c.getProductGroupId()))
                .toList();
    }

    public long countByFilter(CrawlProductFilter crawlProductFilter){
        return crawlProductQueryRepository.countByFilter(crawlProductFilter.toCrawlProductStorageDto());
    }

}
