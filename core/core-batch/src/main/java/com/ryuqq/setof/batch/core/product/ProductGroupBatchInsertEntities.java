package com.ryuqq.setof.batch.core.product;

import com.ryuqq.setof.db.core.product.delivery.ProductDeliveryEntity;
import com.ryuqq.setof.db.core.product.description.ProductDetailDescriptionEntity;
import com.ryuqq.setof.db.core.product.group.ProductGroupConfigEntity;
import com.ryuqq.setof.db.core.product.image.ProductGroupImageEntity;
import com.ryuqq.setof.db.core.product.notice.ProductNoticeEntity;

import java.util.ArrayList;
import java.util.List;

public class ProductGroupBatchInsertEntities {

    private final List<ProductDeliveryEntity> deliveries = new ArrayList<>();
    private final List<ProductNoticeEntity> notices = new ArrayList<>();
    private final List<ProductDetailDescriptionEntity> detailDescriptions = new ArrayList<>();
    private final List<ProductGroupImageEntity> images = new ArrayList<>();
    private final List<ProductGroupConfigEntity> productGroupConfigEntities = new ArrayList<>();



    public void addDelivery(ProductDeliveryEntity delivery) {
        deliveries.add(delivery);
    }

    public void addNotice(ProductNoticeEntity notice) {
        notices.add(notice);
    }

    public void addDetailDescription(ProductDetailDescriptionEntity detailDescription) {
        detailDescriptions.add(detailDescription);
    }

    public void addImages(ProductGroupImageEntity imageEntity) {
        images.add(imageEntity);
    }

    public void addProductGroupConfig(ProductGroupConfigEntity productGroupConfigEntity) {
        productGroupConfigEntities.add(productGroupConfigEntity);
    }

    public List<ProductDeliveryEntity> getDeliveries() {
        return deliveries;
    }

    public List<ProductNoticeEntity> getNotices() {
        return notices;
    }

    public List<ProductDetailDescriptionEntity> getDetailDescriptions() {
        return detailDescriptions;
    }

    public List<ProductGroupImageEntity> getImages() {
        return images;
    }



    public List<ProductGroupConfigEntity> getProductGroupConfigEntities() {
        return productGroupConfigEntities;
    }
}
