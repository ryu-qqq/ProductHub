package com.ryuqq.setof.domain.core.color;

import java.util.List;

public interface ColorQueryService {

    boolean colorExist(long colorId);
    List<ColorRecord> findColors(ColorFilter colorFilter);
    long findColorCount(ColorFilter colorFilter);
}
