package com.ryuqq.setof.domain.core.site.external.mapper.buyma;

import com.ryuqq.setof.domain.core.product.ProductGroupImage;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaImage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Component
public class BuyMaImageGenerator {

    public List<BuyMaImage> getImages(List<ProductGroupImage> productImages) {
        List<BuyMaImage> images = new ArrayList<>();
        int imageSortCounter = 2;

        for (ProductGroupImage img : productImages) {
            if (img.getProductImageType().isMain()) {
                images.add(new BuyMaImage(
                        img.getImageUrl(),
                        1
                ));
            } else {
                images.add(new BuyMaImage(
                        img.getImageUrl(),
                        imageSortCounter
                ));
                imageSortCounter++;
            }
        }

        return images.stream()
                .sorted(Comparator.comparingInt(BuyMaImage::position))
                .toList();
    }

}
