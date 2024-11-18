package com.ryuqq.setof.api.core.controller.v1.site.mapper;

import com.ryuqq.setof.api.core.controller.mapper.AbstractGeneralSliceMapper;
import com.ryuqq.setof.api.core.controller.v1.site.response.CrawlProductResponse;
import com.ryuqq.setof.domain.core.generic.Slice;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CrawlProductSliceMapper extends AbstractGeneralSliceMapper<CrawlProductResponse> {

    @Override
    public Slice<CrawlProductResponse> toSlice(List<CrawlProductResponse> data, int pageSize) {
        return super.toSlice(data, pageSize);
    }

    @Override
    public Slice<CrawlProductResponse> toSlice(List<CrawlProductResponse> data, int pageSize, long totalElements) {
        return super.toSlice(data, pageSize, totalElements);
    }


    @Override
    protected void setCursor(Slice<CrawlProductResponse> slice) {
        if (!slice.isEmpty()) {
            List<CrawlProductResponse> content = slice.getContent();
            CrawlProductResponse t = content.getLast();
            slice.setCursor(t.crawlProductId());
        }
    }
}
