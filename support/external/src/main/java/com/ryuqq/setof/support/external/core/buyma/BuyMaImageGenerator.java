package com.ryuqq.setof.support.external.core.buyma;

import com.ryuqq.setof.support.external.core.ExternalSyncProductImage;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaImageContext;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaImageGroupContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class BuyMaImageGenerator {

    public BuyMaImageGroupContext getImageGroupContext(List<ExternalSyncProductImage> images) {
        List<BuyMaImageContext> buyMaImages = new ArrayList<>();
        int imageSortCounter = 2;

        for (ExternalSyncProductImage img : images) {
            if (img.productImageType().isMain()) {
                buyMaImages.add(new BuyMaImageContext(
                        img.imageUrl(),
                        1
                ));
            } else {
                buyMaImages.add(new BuyMaImageContext(
                        img.imageUrl(),
                        imageSortCounter
                ));
                imageSortCounter++;
            }
        }


        List<BuyMaImageContext> imageContexts = buyMaImages.stream()
                .sorted(Comparator.comparingInt(BuyMaImageContext::position))
                .toList();

        return new BuyMaImageGroupContext(imageContexts);
    }

}
