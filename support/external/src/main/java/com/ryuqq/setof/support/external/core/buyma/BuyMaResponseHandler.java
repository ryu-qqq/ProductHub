package com.ryuqq.setof.support.external.core.buyma;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.support.external.core.SyncResult;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuyMaResponseHandler {

    public SyncResult handleResponse(SiteName siteName, long productGroupId, ResponseEntity<Object> response, String requestBody) {
        HttpStatusCode statusCode = response.getStatusCode();

        if (statusCode.is2xxSuccessful()) {
            return handleSuccess(siteName, productGroupId, requestBody, statusCode.value());
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

    private SyncResult handleSuccess(SiteName siteName, long productGroupId, String requestBody, int statusCode) {
        return new SyncResult(
                siteName,
                statusCode,
                true,
                SyncStatus.REVIEW,
                "Product registered successfully",
                productGroupId,
                "",
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
