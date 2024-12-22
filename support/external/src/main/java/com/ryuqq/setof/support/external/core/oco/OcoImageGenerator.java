package com.ryuqq.setof.support.external.core.oco;

import com.ryuqq.setof.support.external.core.ExternalMallImage;
import com.ryuqq.setof.support.external.core.ExternalMallImageContext;
import com.ryuqq.setof.support.external.core.ExternalSyncProductImage;
import com.ryuqq.setof.support.external.core.buyma.dto.BuyMaImageDto;
import com.ryuqq.setof.support.external.core.oco.dto.OcoImageDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class OcoImageGenerator {

    public ExternalMallImageContext generateImageContext(List<ExternalSyncProductImage> images) {
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
        if(externalMallImages.size() > 4) {
            externalMallImages = externalMallImages.subList(0, 4);
        }

        return new ExternalMallImageContext(externalMallImages);
    }

    public List<OcoImageDto> toOcoImageDto(ExternalMallImageContext imageGroupContext) {
        return imageGroupContext.externalMallImages().stream().sorted(Comparator.comparingInt(ExternalMallImage::order))
                .map(e -> new OcoImageDto(e.imageUrl(), e.order()))
                .toList();
    }


}
