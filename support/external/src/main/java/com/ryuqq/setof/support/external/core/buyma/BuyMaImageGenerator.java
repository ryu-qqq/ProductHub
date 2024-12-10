package com.ryuqq.setof.support.external.core.buyma;

import com.ryuqq.setof.support.external.core.ExternalMallImage;
import com.ryuqq.setof.support.external.core.ExternalMallImageContext;
import com.ryuqq.setof.support.external.core.ExternalMallPreProductContext;
import com.ryuqq.setof.support.external.core.ExternalSyncProductImage;
import com.ryuqq.setof.support.external.core.buyma.dto.BuyMaImageDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class BuyMaImageGenerator {

    public ExternalMallImageContext generateImageContext(List<ExternalSyncProductImage> images){
        List<ExternalMallImage> externalMallImages = new ArrayList<>();
        int imageSortCounter = 2;

        for (ExternalSyncProductImage img : images) {
            if (img.productImageType().isMain()) {
                externalMallImages.add(new ExternalMallImage(
                        img.imageUrl(),
                        img.imageUrl(),
                        1
                ));
            } else {
                externalMallImages.add(new ExternalMallImage(
                        img.imageUrl(),
                        img.imageUrl(),
                        imageSortCounter
                ));
                imageSortCounter++;
            }
        }

        return new ExternalMallImageContext(externalMallImages);
    }


    public List<BuyMaImageDto> toBuyMaImageDto(ExternalMallImageContext imageGroupContext) {
        return imageGroupContext.externalMallImages().stream().sorted(Comparator.comparingInt(ExternalMallImage::order))
                .map(e -> new BuyMaImageDto(e.imageUrl(), e.order()))
                .toList();
    }

}
