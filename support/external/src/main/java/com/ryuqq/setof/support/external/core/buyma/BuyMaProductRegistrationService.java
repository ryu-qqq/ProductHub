package com.ryuqq.setof.support.external.core.buyma;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.external.core.*;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaProductContext;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BuyMaProductRegistrationService implements ExternalMallProductRegistrationService<BuyMaProductContext> {

    private final BuyMaClient buyMaClient;
    private final BuyMaProductResponseHandler buyMaProductResponseHandler;
    private final ExternalProductImageCommandGenerator imageCommandGenerator;
    private final ExternalProductRequestBuilder externalProductRequestBuilder;

    public BuyMaProductRegistrationService(BuyMaClient buyMaClient, BuyMaProductResponseHandler buyMaProductResponseHandler, ExternalProductImageCommandGenerator imageCommandGenerator, ExternalProductRequestBuilder externalProductRequestBuilder) {
        this.buyMaClient = buyMaClient;
        this.buyMaProductResponseHandler = buyMaProductResponseHandler;
        this.imageCommandGenerator = imageCommandGenerator;
        this.externalProductRequestBuilder = externalProductRequestBuilder;
    }

    @Override
    public ExternalMallRegistrationResult registration(long productGroupId, long siteId, BuyMaProductContext buyMaProductContext) {
        ExternalMallRegistrationRequest registrationRequest = externalProductRequestBuilder.buildRequest(productGroupId, siteId, buyMaProductContext);
        try {
            ResponseEntity<BuyMaResponse.Success> successResponseEntity = buyMaClient.sendProduct(buyMaProductContext);
            ExternalMallProductUploadStatus successStatus = buyMaProductResponseHandler.handleSuccess(successResponseEntity);

            List<ExternalMallImageResult> externalMallImageResults = imageCommandGenerator.generateImageCommands(productGroupId, buyMaProductContext);

            return new ExternalMallRegistrationResult(
                    registrationRequest,
                    successStatus,
                    productGroupId,
                    "",
                    siteId,
                    buyMaProductContext.product().name(),
                    BigDecimal.valueOf(buyMaProductContext.product().referencePrice()),
                    BigDecimal.valueOf(buyMaProductContext.product().price()),
                    externalMallImageResults
            );

        } catch (FeignException.FeignClientException ex) {
            ExternalMallProductUploadStatus errorStatus = buyMaProductResponseHandler.handleError(
                    ex.status(),
                    ex.getMessage()
            );
            return new ExternalMallRegistrationResult(registrationRequest, errorStatus, productGroupId,null, siteId, null, null, null, List.of());
        }
    }


    @Override
    public SiteName getSiteName() {
        return SiteName.BUYMA;
    }

}
