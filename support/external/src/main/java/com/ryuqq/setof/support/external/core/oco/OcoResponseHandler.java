package com.ryuqq.setof.support.external.core.oco;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.support.external.core.ExternalMallSyncedOption;
import com.ryuqq.setof.support.external.core.SyncResult;
import com.ryuqq.setof.support.external.core.oco.dto.OcoProductInsertResponseDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class OcoResponseHandler {

    private final ObjectMapper objectMapper;

    public OcoResponseHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public SyncResult handleResponse(SiteName siteName, long productGroupId, String externalProductGroupId, ResponseEntity<OcoResponse<?>> response, String requestBody) {
        HttpStatusCode statusCode = response.getStatusCode();

        if (statusCode.is2xxSuccessful() && response.getBody() != null && response.getBody().responseStatus().statusCode() == 200) {
            OcoProductInsertResponseDto responseDto = objectMapper.convertValue(response.getBody().apiResult(), OcoProductInsertResponseDto.class);

            return handleSuccess(siteName, productGroupId, responseDto, requestBody, statusCode.value());
        } else if (statusCode.is4xxClientError() && response.getBody() != null) {
            String errorMessage =response.getBody().responseStatus().returnMessage();
            return handleFailure(siteName, productGroupId, externalProductGroupId, errorMessage, requestBody, statusCode.value());
        }else if (statusCode.is2xxSuccessful() && response.getBody() != null) {
            String errorMessage =response.getBody().responseStatus().returnMessage();
            return handleFailure(siteName, productGroupId, externalProductGroupId, errorMessage, requestBody, response.getBody().responseStatus().statusCode());
        }

        return new SyncResult(
                siteName,
                statusCode.value(),
                false,
                SyncStatus.FAILED,
                "Unhandled response type or missing body",
                productGroupId,
                "",
                List.of(),
                requestBody
        );
    }

    private SyncResult handleSuccess(SiteName siteName, long productGroupId, OcoProductInsertResponseDto responseDto, String requestBody, int statusCode) {
        List<ExternalMallSyncedOption> list;
        if(responseDto.optionList() == null){
            list = List.of();
        }else{
            list = responseDto.optionList().stream()
                    .map(o -> new ExternalMallSyncedOption(String.valueOf(o.productOptionId()), o.optionData1() + o.optionData2()))
                    .toList();
        }

        return new SyncResult(
                siteName,
                statusCode,
                true,
                SyncStatus.APPROVED,
                "Product registered successfully",
                productGroupId,
                String.valueOf(responseDto.pid()),
                list,
                requestBody
        );
    }

    public SyncResult handleFailure(SiteName siteName, long productGroupId, String externalProductGroupId, String errorMessage, String requestBody, int statusCode) {
        return new SyncResult(
                siteName,
                statusCode,
                false,
                SyncStatus.FAILED,
                errorMessage,
                productGroupId,
                externalProductGroupId,
                List.of(),
                requestBody
        );
    }



}
