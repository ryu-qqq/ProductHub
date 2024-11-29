package com.ryuqq.setof.support.external.core.buyma;

import com.ryuqq.setof.support.external.core.ExternalMallProductImage;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaImage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class BuyMaImageGenerator {

    public List<BuyMaImage> getImages(List<ExternalMallProductImage> images) {
        List<BuyMaImage> buyMaImages = new ArrayList<>();
        int imageSortCounter = 2;

        for (ExternalMallProductImage img : images) {
            if (img.productImageType().isMain()) {
                buyMaImages.add(new BuyMaImage(
                        img.imageUrl(),
                        1
                ));
            } else {
                buyMaImages.add(new BuyMaImage(
                        img.imageUrl(),
                        imageSortCounter
                ));
                imageSortCounter++;
            }
        }

        return buyMaImages.stream()
                .sorted(Comparator.comparingInt(BuyMaImage::position))
                .toList();
    }

}
