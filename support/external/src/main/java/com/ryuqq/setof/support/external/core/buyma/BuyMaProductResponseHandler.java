package com.ryuqq.setof.support.external.core.buyma;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.external.core.SyncResult;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class BuyMaProductResponseHandler {

    public SyncResult handleResponse(SiteName siteName, long productGroupId, ResponseEntity<BuyMaResponse<?>> response, String requestBody) {
        HttpStatusCode statusCode = response.getStatusCode();

        if (statusCode.is2xxSuccessful() && response.getBody() != null && response.getBody().getT() instanceof BuyMaResponse.Success success) {
            return handleSuccess(siteName, productGroupId, success, requestBody, statusCode.value());
        } else if (statusCode.is4xxClientError() && response.getBody() != null && response.getBody().getT() instanceof BuyMaResponse.Failure failure) {
            String errorMessage = failure.getErrors().toString();

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

    private SyncResult handleSuccess(SiteName siteName, long productGroupId, BuyMaResponse.Success success, String requestBody, int statusCode) {
        return new SyncResult(
                siteName,
                statusCode,
                true,
                "Product registered successfully",
                productGroupId,
                success.getRequestUid(),
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
