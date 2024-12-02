package com.ryuqq.setof.support.external.core.buyma;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.external.core.ExternalMallProductRegistrationService;
import com.ryuqq.setof.support.external.core.ExternalMallRequestStatus;
import com.ryuqq.setof.support.external.core.ExternalMallSyncResponse;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaProductContext;
import com.ryuqq.setof.support.utils.JsonUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BuyMaProductRegistrationService implements ExternalMallProductRegistrationService<BuyMaProductContext> {

    private final BuyMaClient buyMaClient;
    private final BuyMaProductResponseHandler buyMaProductResponseHandler;


    public BuyMaProductRegistrationService(BuyMaClient buyMaClient, BuyMaProductResponseHandler buyMaProductResponseHandler) {
        this.buyMaClient = buyMaClient;
        this.buyMaProductResponseHandler = buyMaProductResponseHandler;
    }

    @Override
    public ExternalMallSyncResponse registration(BuyMaProductContext context) {
        try {
            ResponseEntity<BuyMaResponse<Object>> response = buyMaClient.insertProduct(context.toProductInsertRequestDto());
            ExternalMallRequestStatus externalMallRequestStatus = buyMaProductResponseHandler.handleResponse(response);
            return toExternalMallSyncResponse(context, externalMallRequestStatus);
        } catch (Exception e) {
            ExternalMallRequestStatus externalMallRequestStatus = buyMaProductResponseHandler.handleError(500, e.getMessage());
            return toExternalMallSyncResponse(context, externalMallRequestStatus);
        }
    }

    private ExternalMallSyncResponse toExternalMallSyncResponse(BuyMaProductContext context, ExternalMallRequestStatus externalMallRequestStatus){
        return new ExternalMallSyncResponse(
                getSiteName(),
                context.getProductGroupId(),
                context.getSetOfProductGroupId(),
                "",
                context.getDetailContext().getExternalMallNameContext().getName(),
                context.getPriceContext().getRegularPrice(),
                context.getPriceContext().getCurrentPrice(),
                context.toImageRequestResult(),
                context.getTotalQuantity(),
                context.getTotalQuantity() <= 0,
                true,
                JsonUtils.toJson(context),
                externalMallRequestStatus
        );
    }

    @Override
    public SiteName getSiteName() {
        return SiteName.BUYMA;
    }

}
