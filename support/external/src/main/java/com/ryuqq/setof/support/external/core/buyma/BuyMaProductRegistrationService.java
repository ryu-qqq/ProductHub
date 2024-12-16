package com.ryuqq.setof.support.external.core.buyma;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.external.core.ExternalMallPreProductContext;
import com.ryuqq.setof.support.external.core.ExternalMallProductContext;
import com.ryuqq.setof.support.external.core.ExternalMallProductRegistrationService;
import com.ryuqq.setof.support.external.core.SyncResult;
import com.ryuqq.setof.support.external.core.buyma.dto.BuyMaProductInsertRequestDto;
import com.ryuqq.setof.support.utils.JsonUtils;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BuyMaProductRegistrationService implements ExternalMallProductRegistrationService {

    private final BuyMaClient buyMaClient;
    private final BuyMaProductMapper buyMaProductMapper;
    private final BuyMaResponseHandler responseHandler;

    public BuyMaProductRegistrationService(BuyMaClient buyMaClient, BuyMaProductMapper buyMaProductMapper, BuyMaResponseHandler responseHandler) {
        this.buyMaClient = buyMaClient;
        this.buyMaProductMapper = buyMaProductMapper;
        this.responseHandler = responseHandler;
    }

    @Override
    public SyncResult registration(ExternalMallPreProductContext context, ExternalMallProductContext externalMallProductContext) {
        BuyMaProductInsertRequestDto insertRequestDto = buyMaProductMapper.toInsertRequestDto(externalMallProductContext);
        try {
            ResponseEntity<Object> response = buyMaClient.insertProduct(insertRequestDto);
            return responseHandler.handleResponse(
                    getSiteName(),
                    externalMallProductContext.getProductGroupId(),
                    response,
                    JsonUtils.toJson(insertRequestDto)
            );
        } catch (FeignException.FeignClientException e) {
            return responseHandler.handleFailure(
                    getSiteName(),
                    externalMallProductContext.getProductGroupId(),
                    e.getMessage(),
                    JsonUtils.toJson(insertRequestDto),
                    e.status()
            );
        }
    }



    @Override
    public SiteName getSiteName() {
        return SiteName.BUYMA;
    }

}
