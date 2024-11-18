package com.ryuqq.setof.api.core.controller.mapper;


import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.generic.SliceUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class AbstractGeneralSliceMapper <T> implements GeneralSliceMapper<T> {

    @Override
    public Slice<T> toSlice(List<T> data, int pageSize) {
        Slice<T> slice = SliceUtils.toSlice(data, pageSize);
        setCursor(slice);
        return slice;
    }

    @Override
    public Slice<T> toSlice(List<T> data, int pageSize, long totalElements) {
        Slice<T> slice = SliceUtils.toSlice(data, pageSize, totalElements);
        setCursor(slice);
        return slice;
    }

    protected abstract void setCursor(Slice<T> slice);

}

