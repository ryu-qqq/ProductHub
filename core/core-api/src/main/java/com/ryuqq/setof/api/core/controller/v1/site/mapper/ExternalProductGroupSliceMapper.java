package com.ryuqq.setof.api.core.controller.v1.site.mapper;

import com.ryuqq.setof.api.core.controller.mapper.AbstractGeneralSliceMapper;
import com.ryuqq.setof.api.core.controller.v1.site.response.ExternalProductGroupContextResponse;
import com.ryuqq.setof.domain.core.generic.Slice;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalProductGroupSliceMapper extends AbstractGeneralSliceMapper<ExternalProductGroupContextResponse> {

    @Override
    public Slice<ExternalProductGroupContextResponse> toSlice(List<ExternalProductGroupContextResponse> data, int pageSize) {
        return super.toSlice(data, pageSize);
    }

    @Override
    public Slice<ExternalProductGroupContextResponse> toSlice(List<ExternalProductGroupContextResponse> data, int pageSize, long totalElements) {
        return super.toSlice(data, pageSize, totalElements);
    }

    @Override
    protected void setCursor(Slice<ExternalProductGroupContextResponse> slice) {
        if (!slice.isEmpty()) {
            List<ExternalProductGroupContextResponse> content = slice.getContent();
            ExternalProductGroupContextResponse t = content.getLast();
            //slice.setCursor(t.productGroup().productGroupId());
        }
    }

}
