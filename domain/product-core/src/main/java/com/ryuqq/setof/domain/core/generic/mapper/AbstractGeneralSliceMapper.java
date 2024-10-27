package com.ryuqq.setof.domain.core.generic.mapper;


import com.ryuqq.setof.domain.core.generic.LastDomainIdProvider;
import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.generic.SliceUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class AbstractGeneralSliceMapper <T extends LastDomainIdProvider> implements GeneralSliceMapper<T>{

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

    protected void setCursor(Slice<T> slice){
        if (!slice.isEmpty()) {
            List<T> content = slice.getContent();
            T t = content.getLast();
            slice.setCursor(t.getId());
        }
    }

}

