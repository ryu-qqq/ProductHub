package com.ryuqq.setof.storage.db.core.color;

import com.ryuqq.setof.storage.db.core.color.dto.ColorDto;
import com.ryuqq.setof.storage.db.core.color.dto.ColorStorageFilterDto;

import java.util.List;

public interface ColorQueryRepository {

    boolean fetchColorExists(long colorId);
    List<ColorDto> fetchColors(ColorStorageFilterDto colorFilter);
    long fetchColorCount(ColorStorageFilterDto colorFilter);

}
