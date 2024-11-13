package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.storage.db.core.site.CrawlProductQueryRepository;
import com.ryuqq.setof.storage.db.core.site.dto.CrawlProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrawlProductFinder implements CrawlProductQueryService {

    private final CrawlProductQueryRepository crawlProductQueryRepository;

    public CrawlProductFinder(CrawlProductQueryRepository crawlProductQueryRepository) {
        this.crawlProductQueryRepository = crawlProductQueryRepository;
    }

    public List<CrawlProduct> getCrawlProducts(CrawlProductFilter crawlProductFilter){
        List<CrawlProductDto> crawlProductDtos = crawlProductQueryRepository.fetchCrawlProducts(crawlProductFilter.toCrawlProductStorageDto());
        return crawlProductDtos.stream()
                .map(c ->
                        new CrawlProduct(c.getCrawlProductId(), c.getSiteId(), c.getSiteName(),
                                c.getSiteProductId(), c.getProductName(), c.getProductGroupId()))
                .toList();
    }

    public long getCrawlProductCount(CrawlProductFilter crawlProductFilter){
        return crawlProductQueryRepository.fetchCrawlProductCount(crawlProductFilter.toCrawlProductStorageDto());
    }

}
