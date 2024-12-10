package com.ryuqq.setof.support.external.core.sellic;

import com.ryuqq.setof.support.external.core.ExternalMallImage;
import com.ryuqq.setof.support.external.core.ExternalMallImageContext;
import com.ryuqq.setof.support.external.core.ExternalSyncProductImage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class SellicImageGenerator {

    public ExternalMallImageContext generateImageContext(List<ExternalSyncProductImage> images){
        List<ExternalMallImage> sellicImages = new ArrayList<>();
        int imageSortCounter = 2;

        for (ExternalSyncProductImage img : images) {
            if (img.productImageType().isMain()) {
                sellicImages.add(new ExternalMallImage(
                        img.imageUrl(),
                        img.imageUrl(),
                        1
                ));
            } else {
                sellicImages.add(new ExternalMallImage(
                        img.imageUrl(),
                        img.imageUrl(),
                        imageSortCounter
                ));
                imageSortCounter++;
            }
        }

        List<ExternalMallImage> imageContexts = sellicImages.stream()
                .sorted(Comparator.comparingInt(ExternalMallImage::order))
                .toList();

        return new ExternalMallImageContext(imageContexts);
    }

}
