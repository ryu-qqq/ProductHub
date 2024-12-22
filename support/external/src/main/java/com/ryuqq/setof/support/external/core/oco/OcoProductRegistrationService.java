package com.ryuqq.setof.support.external.core.oco;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.external.core.ExternalMallPreProductContext;
import com.ryuqq.setof.support.external.core.ExternalMallProductContext;
import com.ryuqq.setof.support.external.core.ExternalMallProductRegistrationService;
import com.ryuqq.setof.support.external.core.SyncResult;
import com.ryuqq.setof.support.external.core.buyma.BuyMaResponse;
import com.ryuqq.setof.support.external.core.oco.dto.OcoProductInsertRequestDto;
import com.ryuqq.setof.support.external.core.oco.dto.OcoProductInsertResponseDto;
import com.ryuqq.setof.support.external.core.oco.dto.OcoProductUpdateRequestDto;
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
        String requestBody = "";
        try {
            OcoProductInsertRequestDto insertRequestDto = ocoProductMapper.toInsertRequestDto(context, externalMallProductContext);
            ResponseEntity<OcoResponse<?>> response;

            if (insertRequestDto.product().getPid() != null) {
                OcoProductUpdateRequestDto updateRequestDto = insertRequestDto.toOcoProductUpdateRequestDto();
                requestBody = JsonUtils.toJson(updateRequestDto);
                response = ocoClient.updateProduct(updateRequestDto);
            } else {
                requestBody = JsonUtils.toJson(insertRequestDto);
                response = ocoClient.insertProduct(insertRequestDto);
            }

            return responseHandler.handleResponse(
                    getSiteName(),
                    externalMallProductContext.getProductGroupId(),
                    externalMallProductContext.getExternalProductGroupId(),
                    response,
                    requestBody
            );
        } catch (FeignException.FeignClientException e) {
            return responseHandler.handleFailure(
                    getSiteName(),
                    externalMallProductContext.getProductGroupId(),
                    externalMallProductContext.getExternalProductGroupId(),
                    e.getMessage(),
                    requestBody,
                    e.status()
            );
        }
    }

    @Override
    public SiteName getSiteName() {
        return SiteName.OCO;
    }
}
