package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.storage.db.core.product.image.ProductGroupImageEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductGroupImageUpdater {

    private final List<ProductGroupImageEntity> toInsert = new ArrayList<>();
    private final List<ProductGroupImageEntity> toUpdate = new ArrayList<>();

    public ProductGroupImageUpdater(long productGroupId, List<ProductGroupImage> existingImages, List<ProductGroupImageCommand> productGroupImageCommands) {

        Map<String, ProductGroupImage> existingMap = existingImages.stream()
                .collect(Collectors.toMap(ProductGroupImage::getOriginUrl, Function.identity()));

        for (ProductGroupImageCommand command : productGroupImageCommands) {
            ProductGroupImage existingImage = existingMap.get(command.imageUrl());

            if (existingImage != null) {
                if (existingImage.needsUpdate(command.imageUrl())) {
                    existingImage.updateFromCommand(command.imageUrl());
                    toUpdate.add(existingImage.toEntity());
                }
                existingMap.remove(command.imageUrl());
            } else {
                toInsert.add(command.toEntity(productGroupId));
            }
        }

        existingMap.values().forEach(
                productGroupImage -> {
                    productGroupImage.delete();
                    toUpdate.add(productGroupImage.toEntity());
                }
        );
    }

    public List<ProductGroupImageEntity> getToInsert() {
        return toInsert;
    }

    public List<ProductGroupImageEntity> getToUpdate() {
        return toUpdate;
    }
}
