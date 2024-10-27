package com.ryuqq.setof.storage.db.core.data;

import com.ryuqq.setof.storage.db.core.brand.BrandEntity;
import com.ryuqq.setof.storage.db.core.brand.dto.BrandStorageFilterDto;
import com.ryuqq.setof.storage.db.core.data.utils.EasyRandomUtils;
import org.jeasy.random.EasyRandom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrandModuleHelper {

    public static BrandEntity toBrandWithNoId(){
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("brandName", "Nike");
        stringObjectMap.put("brandIconImageUrl", "https://test.jpg");
        stringObjectMap.put("displayYn", true);

        EasyRandom instance = EasyRandomUtils.getInstanceWithNoId(stringObjectMap);
        return instance.nextObject(BrandEntity.class);
    }


    public static BrandStorageFilterDto toBrandFilter(List<Long> brandIds){
        return new BrandStorageFilterDto(brandIds, null, 20);
    }


}
