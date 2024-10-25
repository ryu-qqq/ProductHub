package com.ryuqq.setof.domain.core.brand;

import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.generic.mapper.AbstractGeneralSliceMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BrandSliceMapper extends AbstractGeneralSliceMapper<Brand> {

    @Override
    public Slice<Brand> toSlice(List<Brand> data, int pageSize, long totalElements) {
        return super.toSlice(data, pageSize, totalElements);
    }

    @Override
    public Slice<Brand> toSlice(List<Brand> data, int pageSize) {
        return super.toSlice(data, pageSize);
    }
}
