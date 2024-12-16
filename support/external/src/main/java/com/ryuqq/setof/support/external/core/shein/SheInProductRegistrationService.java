package com.ryuqq.setof.support.external.core.shein;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.external.core.ExternalMallPreProductContext;
import com.ryuqq.setof.support.external.core.ExternalMallProductContext;
import com.ryuqq.setof.support.external.core.ExternalMallProductRegistrationService;
import com.ryuqq.setof.support.external.core.SyncResult;
import com.ryuqq.setof.support.external.core.dto.SheInProductInsertRequestDto;
import com.ryuqq.setof.support.external.core.dto.SheInProductInsertResponseDto;
import com.ryuqq.setof.support.utils.JsonUtils;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SheInProductRegistrationService implements ExternalMallProductRegistrationService {

    private final SheInClient sheInClient;
    private final SheInProductMapper sheInProductMapper;
    private final SheInSyncResponseHandler responseHandler;

    public SheInProductRegistrationService(SheInClient sheInClient, SheInProductMapper sheInProductMapper, SheInSyncResponseHandler responseHandler) {
        this.sheInClient = sheInClient;
        this.sheInProductMapper = sheInProductMapper;
        this.responseHandler = responseHandler;
    }


    @Override
    public SyncResult registration(ExternalMallPreProductContext context, ExternalMallProductContext externalMallProductContext) {
        SheInProductInsertRequestDto insertRequestDto = sheInProductMapper.toInsertRequestDto(externalMallProductContext);
        try {
            ResponseEntity<SheInResponse<SheInProductInsertResponseDto>> response = sheInClient.insertProduct(insertRequestDto);
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
        return SiteName.SHEIN;
    }

}
