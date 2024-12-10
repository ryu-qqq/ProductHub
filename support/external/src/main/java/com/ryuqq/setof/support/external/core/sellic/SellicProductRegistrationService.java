package com.ryuqq.setof.support.external.core.sellic;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.external.core.ExternalMallPreProductContext;
import com.ryuqq.setof.support.external.core.ExternalMallProductContext;
import com.ryuqq.setof.support.external.core.ExternalMallProductRegistrationService;
import com.ryuqq.setof.support.external.core.SyncResult;
import com.ryuqq.setof.support.external.core.oco.OcoResponse;
import com.ryuqq.setof.support.external.core.oco.dto.OcoProductInsertRequestDto;
import com.ryuqq.setof.support.external.core.sellic.dto.SellicProductInsertRequestDto;
import com.ryuqq.setof.support.utils.JsonUtils;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SellicProductRegistrationService implements ExternalMallProductRegistrationService {

    private final SellicClient sellicClient;
    private final SellicProductMapper sellicProductMapper;
    private final SellicResponseHandler responseHandler;

    public SellicProductRegistrationService(SellicClient sellicClient, SellicProductMapper sellicProductMapper, SellicResponseHandler responseHandler) {
        this.sellicClient = sellicClient;
        this.sellicProductMapper = sellicProductMapper;
        this.responseHandler = responseHandler;
    }

    @Override
    public SyncResult registration(ExternalMallPreProductContext context, ExternalMallProductContext externalMallProductContext) {
        SellicProductInsertRequestDto insertRequestDto = sellicProductMapper.toInsertRequestDto(context, externalMallProductContext);
        try {
            ResponseEntity<SellicResponse> response = sellicClient.insertProduct(insertRequestDto);
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
        return SiteName.SELLIC;
    }
}
