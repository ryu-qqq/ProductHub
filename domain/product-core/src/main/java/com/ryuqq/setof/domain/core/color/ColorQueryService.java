package com.ryuqq.setof.domain.core.color;

import java.util.List;

public interface ColorQueryService {

    boolean existById(long colorId);
    List<Color> fetchColorByFilter(ColorFilter colorFilter);
    long countByFilter(ColorFilter colorFilter);
}
