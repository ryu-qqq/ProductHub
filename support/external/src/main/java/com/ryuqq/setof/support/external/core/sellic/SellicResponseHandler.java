package com.ryuqq.setof.support.external.core.sellic;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.support.external.core.SyncResult;
import com.ryuqq.setof.support.external.core.oco.OcoResponse;
import com.ryuqq.setof.support.external.core.oco.dto.OcoProductInsertResponseDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SellicResponseHandler {

    public SyncResult handleResponse(SiteName siteName, long productGroupId, ResponseEntity<SellicResponse> response, String requestBody) {
        HttpStatusCode statusCode = response.getStatusCode();

        if (statusCode.is2xxSuccessful() && response.getBody() != null&& response.getBody().productId() != null) {
            return handleSuccess(siteName, productGroupId, response.getBody(), requestBody, statusCode.value());
        } else if (statusCode.is4xxClientError() && response.getBody() != null) {
            String errorMessage =response.getBody().message();
            return handleFailure(siteName, productGroupId, errorMessage, requestBody, statusCode.value());
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

    private SyncResult handleSuccess(SiteName siteName, long productGroupId, SellicResponse responseDto, String requestBody, int statusCode) {
        return new SyncResult(
                siteName,
                statusCode,
                true,
                SyncStatus.APPROVED,
                "Product registered successfully",
                productGroupId,
                responseDto.productId(),
                List.of(),
                requestBody
        );
    }

    public SyncResult handleFailure(SiteName siteName, long productGroupId, String errorMessage, String requestBody, int statusCode) {
        return new SyncResult(
                siteName,
                statusCode,
                false,
                SyncStatus.FAILED,
                errorMessage,
                productGroupId,
                "",
                List.of(),
                requestBody
        );
    }

}
