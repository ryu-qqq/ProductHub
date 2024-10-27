package com.ryuqq.setof.domain.core.generic.mapper;


import com.ryuqq.setof.domain.core.generic.LastDomainIdProvider;
import com.ryuqq.setof.domain.core.generic.Slice;

import java.util.List;

public interface GeneralSliceMapper<T extends LastDomainIdProvider> {

    Slice<T> toSlice(List<T> data, int pageSize);
    Slice<T> toSlice(List<T> data, int pageSize, long totalElements);

}


