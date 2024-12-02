package com.ryuqq.setof.api.core.controller.v1.site.mapper;

import com.ryuqq.setof.api.core.controller.mapper.AbstractGeneralSliceMapper;
import com.ryuqq.setof.api.core.controller.v1.site.response.ExternalProductContextResponse;
import com.ryuqq.setof.domain.core.generic.Slice;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalProductSliceMapper extends AbstractGeneralSliceMapper<ExternalProductContextResponse> {

    @Override
    public Slice<ExternalProductContextResponse> toSlice(List<ExternalProductContextResponse> data, int pageSize) {
        return super.toSlice(data, pageSize);
    }

    @Override
    public Slice<ExternalProductContextResponse> toSlice(List<ExternalProductContextResponse> data, int pageSize, long totalElements) {
        return super.toSlice(data, pageSize, totalElements);
    }

    @Override
    protected void setCursor(Slice<ExternalProductContextResponse> slice) {
        if (!slice.isEmpty()) {
            List<ExternalProductContextResponse> content = slice.getContent();
            ExternalProductContextResponse t = content.getLast();
            //slice.setCursor(t.productGroup().productGroupId());
        }
    }

}
