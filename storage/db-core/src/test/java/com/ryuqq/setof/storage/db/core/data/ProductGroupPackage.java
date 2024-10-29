package com.ryuqq.setof.storage.db.core.data;

import com.ryuqq.setof.storage.db.core.product.delivery.ProductDeliveryEntity;
import com.ryuqq.setof.storage.db.core.product.description.ProductDetailDescriptionEntity;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupEntity;
import com.ryuqq.setof.storage.db.core.product.image.ProductGroupImageEntity;
import com.ryuqq.setof.storage.db.core.product.notice.ProductNoticeEntity;
import com.ryuqq.setof.storage.db.core.product.option.ProductEntity;
import com.ryuqq.setof.storage.db.core.product.option.detail.OptionDetailEntity;
import com.ryuqq.setof.storage.db.core.product.option.group.OptionGroupEntity;
import com.ryuqq.setof.storage.db.core.product.option.mapping.ProductOptionEntity;

import java.util.List;

public record ProductGroupPackage(
        ProductGroupEntity productGroupEntity,
        ProductNoticeEntity productNoticeEntity,
        ProductDeliveryEntity productDelivery,
        ProductDetailDescriptionEntity productDetailDescriptionEntity,
        List<ProductGroupImageEntity> productGroupImageEntities,
        List<ProductEntity> productEntities,
        List<ProductOptionEntity> productOptionEntities,
        List<OptionGroupEntity> optionGroupEntities,
        List<OptionDetailEntity> optionDetailEntities
) {}
