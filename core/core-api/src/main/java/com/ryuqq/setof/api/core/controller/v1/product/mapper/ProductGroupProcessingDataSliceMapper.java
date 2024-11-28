package com.ryuqq.setof.api.core.controller.v1.product.mapper;

import com.ryuqq.setof.api.core.controller.mapper.AbstractGeneralSliceMapper;
import com.ryuqq.setof.api.core.controller.v1.product.response.GptTrainingDataResponse;
import com.ryuqq.setof.domain.core.generic.Slice;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGroupProcessingDataSliceMapper extends AbstractGeneralSliceMapper<GptTrainingDataResponse> {

    @Override
    public Slice<GptTrainingDataResponse> toSlice(List<GptTrainingDataResponse> data, int pageSize) {
        return super.toSlice(data, pageSize);
    }

    @Override
    public Slice<GptTrainingDataResponse> toSlice(List<GptTrainingDataResponse> data, int pageSize, long totalElements) {
        return super.toSlice(data, pageSize, totalElements);
    }

    @Override
    protected void setCursor(Slice<GptTrainingDataResponse> slice) {
        if (!slice.isEmpty()) {
            List<GptTrainingDataResponse> content = slice.getContent();
            GptTrainingDataResponse t = content.getLast();
            slice.setCursor(t.productGroup().productGroupId());
        }
    }


}
