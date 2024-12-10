package com.ryuqq.setof.support.external.core.shein;

import com.ryuqq.setof.support.external.core.ExternalSyncProductImage;
import com.ryuqq.setof.support.external.core.shein.domain.SheInImageContext;
import com.ryuqq.setof.support.external.core.shein.domain.SheInImageGroupContext;
import com.ryuqq.setof.support.external.core.shein.dto.SheInImageUploadRequestDto;
import com.ryuqq.setof.support.external.core.shein.dto.SheInImageUploadResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class SheInImageGenerator {

    private final SheInClient sheInClient;
    private final SheInProductResponseHandler sheInProductResponseHandler;

    public SheInImageGenerator(SheInClient sheInClient, SheInProductResponseHandler sheInProductResponseHandler) {
        this.sheInClient = sheInClient;
        this.sheInProductResponseHandler = sheInProductResponseHandler;
    }


    public SheInImageGroupContext getImageGroupContext(List<ExternalSyncProductImage> images) {
//        List<SheInImageContext> sheInImages = new ArrayList<>();
//        int imageSortCounter = 2;
//
//        for (ExternalSyncProductImage img : images) {
//            int sortOrder = img.productImageType().isMain() ? 1 : imageSortCounter;
//            int imageType = img.productImageType().isMain() ? 1 : 2;
//
//            SheInImageContext sheInImageContext = processImage(img.imageUrl(), sortOrder, imageType);
//            sheInImages.add(sheInImageContext);
//
//            if (!img.productImageType().isMain()) {
//                imageSortCounter++;
//            }
//        }

        //return createImageGroupContext(sheInImages);
        return null;
    }

//    private SheInImageContext processImage(String imageUrl, int sortOrder, int imageType) {
//        ResponseEntity<SheInResponse<SheInImageUploadResponse>> responseEntity =
//                sheInClient.uploadImages(new SheInImageUploadRequestDto(imageType, imageUrl));
//
//        ExternalMallRequestStatus<SheInImageUploadResponse> response =
//                sheInProductResponseHandler.handleResponse(responseEntity);
//
//        if(!response.success()) throw new IllegalArgumentException(response.errorDetails());
//
//        String transformed = response.responseBody().transformed();
//
//        return new SheInImageContext(null, sortOrder, imageType, transformed, imageUrl);
//    }
//
//    private SheInImageGroupContext createImageGroupContext(List<SheInImageContext> sheInImages) {
//        List<SheInImageContext> sortedImages = sheInImages.stream()
//                .sorted(Comparator.comparingInt(SheInImageContext::imageSort))
//                .limit(5)
//                .toList();
//
//        return new SheInImageGroupContext(null, sortedImages);
//    }
}
