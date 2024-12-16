package com.ryuqq.setof.support.external.core.shein;

import com.ryuqq.setof.support.external.core.ExternalMallImage;
import com.ryuqq.setof.support.external.core.ExternalMallImageContext;
import com.ryuqq.setof.support.external.core.ExternalSyncProductImage;
import com.ryuqq.setof.support.external.core.dto.SheInImageDto;
import com.ryuqq.setof.support.external.core.dto.SheInImageGroupDto;
import com.ryuqq.setof.support.external.core.dto.SheInImageUploadRequestDto;
import com.ryuqq.setof.support.external.core.dto.SheInImageUploadResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class SheInImageGenerator {

    private final SheInClient sheInClient;
    private final SheInResponseHandler sheInResponseHandler;

    public SheInImageGenerator(SheInClient sheInClient, SheInResponseHandler sheInResponseHandler) {
        this.sheInClient = sheInClient;
        this.sheInResponseHandler = sheInResponseHandler;
    }

    public ExternalMallImageContext generateImageContext(List<ExternalSyncProductImage> images) {
        List<ExternalMallImage> externalMallImages = new ArrayList<>();
        int imageSortCounter = 2;

        for (ExternalSyncProductImage img : images) {
            int sortOrder = img.productImageType().isMain() ? 1 : imageSortCounter;
            int imageType = img.productImageType().isMain() ? 1 : 2;

            ExternalMallImage externalMallImage = processImage(img.imageUrl(), sortOrder, imageType);
            externalMallImages.add(externalMallImage);

            if (!img.productImageType().isMain()) {
                imageSortCounter++;
            }
        }

        externalMallImages.sort(Comparator.comparing(ExternalMallImage::order));
        return new ExternalMallImageContext(externalMallImages);
    }

    private ExternalMallImage processImage(String imageUrl, int sortOrder, int imageType) {
        ResponseEntity<SheInResponse<SheInImageUploadResponseDto>> response =
                sheInClient.uploadImages(new SheInImageUploadRequestDto(imageType, imageUrl));

        SheInResponse<SheInImageUploadResponseDto> sheInResponse = sheInResponseHandler.handleResponse(response);
        return new ExternalMallImage(sheInResponse.getInfo().getImageUrl(), imageUrl, sortOrder);
    }

    public SheInImageGroupDto toSheInImageGroupDto(ExternalMallImageContext externalMallImageContext){
        List<SheInImageDto> sheInImageDtos = new ArrayList<>();

        for(ExternalMallImage image : externalMallImageContext.externalMallImages()){
            int imageType = image.order() == 1 ? 1 : 2;
            sheInImageDtos.add(new SheInImageDto(null, image.order(), imageType, image.imageUrl()));

        }

        return new SheInImageGroupDto(null, sheInImageDtos);
    }


}
