package com.ryuqq.setof.storage.db.core.color;

import com.ryuqq.setof.storage.db.core.color.dto.ColorDto;
import com.ryuqq.setof.storage.db.core.color.dto.ColorStorageFilterDto;

import java.util.List;

public interface ColorQueryRepository {

    boolean existById(long colorId);
    List<ColorDto> fetchColorByFilter(ColorStorageFilterDto colorFilter);
    long countByFilter(ColorStorageFilterDto colorFilter);

}
