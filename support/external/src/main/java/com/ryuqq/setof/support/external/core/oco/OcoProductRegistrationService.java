package com.ryuqq.setof.support.external.core.oco;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.external.core.ExternalMallPreProductContext;
import com.ryuqq.setof.support.external.core.ExternalMallProductContext;
import com.ryuqq.setof.support.external.core.ExternalMallProductRegistrationService;
import com.ryuqq.setof.support.external.core.SyncResult;
import com.ryuqq.setof.support.external.core.buyma.BuyMaResponse;
import com.ryuqq.setof.support.external.core.oco.dto.OcoProductInsertRequestDto;
import com.ryuqq.setof.support.external.core.oco.dto.OcoProductInsertResponseDto;
import com.ryuqq.setof.support.utils.JsonUtils;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OcoProductRegistrationService implements ExternalMallProductRegistrationService {

    private final OcoClient ocoClient;
    private final OcoProductMapper ocoProductMapper;
    private final OcoResponseHandler responseHandler;

    public OcoProductRegistrationService(OcoClient ocoClient, OcoProductMapper ocoProductMapper, OcoResponseHandler responseHandler) {
        this.ocoClient = ocoClient;
        this.ocoProductMapper = ocoProductMapper;
        this.responseHandler = responseHandler;
    }

    @Override
    public SyncResult registration(ExternalMallPreProductContext context, ExternalMallProductContext externalMallProductContext) {
        OcoProductInsertRequestDto insertRequestDto = ocoProductMapper.toInsertRequestDto(context, externalMallProductContext);
        try {
            ResponseEntity<OcoResponse<?>> response = ocoClient.insertProduct(insertRequestDto);
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
        return SiteName.OCO;
    }
}
