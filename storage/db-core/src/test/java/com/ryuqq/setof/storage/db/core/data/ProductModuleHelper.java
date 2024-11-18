package com.ryuqq.setof.storage.db.core.data;

import com.ryuqq.setof.enums.core.OptionName;
import com.ryuqq.setof.enums.core.OptionType;
import com.ryuqq.setof.enums.core.ProductImageType;
import com.ryuqq.setof.storage.db.core.data.utils.EasyRandomUtils;
import com.ryuqq.setof.storage.db.core.product.delivery.ProductDeliveryEntity;
import com.ryuqq.setof.storage.db.core.product.description.ProductDetailDescriptionEntity;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupEntity;
import com.ryuqq.setof.storage.db.core.product.image.ProductGroupImageEntity;
import com.ryuqq.setof.storage.db.core.product.notice.ProductNoticeEntity;
import com.ryuqq.setof.storage.db.core.product.option.ProductEntity;
import com.ryuqq.setof.storage.db.core.product.option.group.OptionGroupEntity;
import org.jeasy.random.EasyRandom;

import java.util.*;

public class ProductModuleHelper {


    public static ProductGroupEntity toProductGroupEntity(OptionType optionType, String styleCode){
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("optionType", optionType);
        if(styleCode != null && !styleCode.isBlank()){
            stringObjectMap.put("styleCode", styleCode);
        }

        EasyRandom instance = EasyRandomUtils.getInstanceWithNoId(stringObjectMap);
        return instance.nextObject(ProductGroupEntity.class);
    }

    public static ProductDeliveryEntity toProductDeliveryEntity(Map<String, Object> stringObjectMap){
        EasyRandom instance = EasyRandomUtils.getInstanceWithNoId(stringObjectMap);
        return instance.nextObject(ProductDeliveryEntity.class);
    }

    public static ProductNoticeEntity toProductNoticeEntity(Map<String, Object> stringObjectMap){
        EasyRandom instance = EasyRandomUtils.getInstanceWithNoId(stringObjectMap);
        return instance.nextObject(ProductNoticeEntity.class);
    }

    public static ProductDetailDescriptionEntity toProductDetailDescription(Map<String, Object> stringObjectMap){
        EasyRandom instance = EasyRandomUtils.getInstanceWithNoId(stringObjectMap);
        return instance.nextObject(ProductDetailDescriptionEntity.class);
    }

    public static ProductGroupImageEntity toProductGroupImageEntity(Map<String, Object> stringObjectMap){
        stringObjectMap.put("productImageType", ProductImageType.MAIN);
        EasyRandom instance = EasyRandomUtils.getInstanceWithNoId(stringObjectMap);
        return instance.nextObject(ProductGroupImageEntity.class);
    }


    public static List<ProductEntity> toProductEntities(Map<String, Object> stringObjectMap, OptionType optionType){
        if (Objects.requireNonNull(optionType) == OptionType.SINGLE) {
            EasyRandom instance = EasyRandomUtils.getInstanceWithNoId(stringObjectMap);
            ProductEntity productEntity = instance.nextObject(ProductEntity.class);
            return List.of(productEntity);
        }

        EasyRandom instance = EasyRandomUtils.getInstanceWithNoId(stringObjectMap);
        ProductEntity productEntity1 = instance.nextObject(ProductEntity.class);
        ProductEntity productEntity2 = instance.nextObject(ProductEntity.class);

        return List.of(productEntity1, productEntity2);
    }


    public static List<OptionGroupEntity> toOptionGroupEntities(OptionType optionType){
        if (Objects.requireNonNull(optionType) == OptionType.OPTION_ONE) {
            return List.of(new OptionGroupEntity(OptionName.SIZE));
        }else{
            return List.of(new OptionGroupEntity(OptionName.COLOR), new OptionGroupEntity(OptionName.SIZE));
        }
    }

//    public static List<OptionDetailEntity> toOptionDetailEntities(long optionGroupId, OptionType optionType){
//
//        if (Objects.requireNonNull(optionType) == OptionType.OPTION_ONE) {
//            return List.of(new OptionDetailEntity(optionGroupId, "M"), new OptionDetailEntity(optionGroupId, "L"));
//        }else{
//            return List.of(
//                    new OptionDetailEntity(optionGroupId, "M"),
//                    new OptionDetailEntity(optionGroupId, "L")
//
//
//
//            );
//        }
//
//
//    }








}
