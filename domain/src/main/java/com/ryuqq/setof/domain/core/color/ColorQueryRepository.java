package com.ryuqq.setof.domain.core.color;

import java.util.List;

public interface ColorQueryRepository {

    boolean fetchColorExists(long colorId);
    List<Color> fetchColors(ColorFilter colorFilter);
    long fetchColorCount(ColorFilter colorFilter);

}
