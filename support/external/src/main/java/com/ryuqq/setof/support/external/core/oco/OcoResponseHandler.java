package com.ryuqq.setof.support.external.core.oco;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.external.core.SyncResult;
import com.ryuqq.setof.support.external.core.oco.dto.OcoProductInsertResponseDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class OcoResponseHandler {

    public SyncResult handleResponse(SiteName siteName, long productGroupId, ResponseEntity<OcoResponse<?>> response, String requestBody) {
        HttpStatusCode statusCode = response.getStatusCode();

        if (statusCode.is2xxSuccessful() && response.getBody() != null && response.getBody().apiResult() instanceof OcoProductInsertResponseDto responseDto) {
            return handleSuccess(siteName, productGroupId, responseDto, requestBody, statusCode.value());
        } else if (statusCode.is4xxClientError() && response.getBody() != null) {
            String errorMessage =response.getBody().responseStatus().returnMessage();
            return handleFailure(siteName, productGroupId, errorMessage, requestBody, statusCode.value());
        }

        return new SyncResult(
                siteName,
                statusCode.value(),
                false,
                "Unhandled response type or missing body",
                productGroupId,
                "",
                requestBody
        );
    }

    private SyncResult handleSuccess(SiteName siteName, long productGroupId, OcoProductInsertResponseDto responseDto, String requestBody, int statusCode) {
        return new SyncResult(
                siteName,
                statusCode,
                true,
                "Product registered successfully",
                productGroupId,
                responseDto.ocoCode(),
                requestBody
        );
    }

    public SyncResult handleFailure(SiteName siteName, long productGroupId, String errorMessage, String requestBody, int statusCode) {
        return new SyncResult(
                siteName,
                statusCode,
                false,
                errorMessage,
                productGroupId,
                "",
                requestBody
        );
    }

}
