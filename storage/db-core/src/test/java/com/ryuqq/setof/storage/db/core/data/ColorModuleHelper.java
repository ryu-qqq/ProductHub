package com.ryuqq.setof.storage.db.core.data;

import com.ryuqq.setof.domain.core.color.ColorFilter;
import com.ryuqq.setof.storage.db.core.color.ColorEntity;
import com.ryuqq.setof.storage.db.core.data.utils.EasyRandomUtils;
import org.jeasy.random.EasyRandom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColorModuleHelper {

    public static ColorEntity toColorWithNoId(){
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("colorName", "Black");

        EasyRandom instance = EasyRandomUtils.getInstanceWithNoId(stringObjectMap);
        return instance.nextObject(ColorEntity.class);
    }


    public static ColorFilter toColorFilter(List<Long> colorIds){
        return new ColorFilter(colorIds, null, 20);
    }

}
