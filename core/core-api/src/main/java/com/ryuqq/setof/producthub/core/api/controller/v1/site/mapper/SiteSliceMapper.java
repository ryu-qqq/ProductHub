package com.ryuqq.setof.producthub.core.api.controller.v1.site.mapper;

import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.producthub.core.api.controller.mapper.AbstractGeneralSliceMapper;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.response.SiteResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SiteSliceMapper extends AbstractGeneralSliceMapper<SiteResponse> {

    @Override
    public Slice<SiteResponse> toSlice(List<SiteResponse> data, int pageSize) {
        return super.toSlice(data, pageSize);
    }

    @Override
    public Slice<SiteResponse> toSlice(List<SiteResponse> data, int pageSize, long totalElements) {
        return super.toSlice(data, pageSize, totalElements);
    }

    @Override
    protected void setCursor(Slice<SiteResponse> slice) {
        if (!slice.isEmpty()) {
            List<SiteResponse> content = slice.getContent();
            SiteResponse t = content.getLast();
            slice.setCursor(t.siteId());
        }
    }
}
