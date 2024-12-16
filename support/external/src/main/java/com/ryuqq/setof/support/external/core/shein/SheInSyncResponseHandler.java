package com.ryuqq.setof.support.external.core.shein;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.support.external.core.SyncResult;
import com.ryuqq.setof.support.external.core.dto.SheInProductInsertResponseDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@Component
public class SheInSyncResponseHandler extends SheInResponseHandler{

    public SyncResult handleResponse(SiteName siteName, long productGroupId, ResponseEntity<SheInResponse<SheInProductInsertResponseDto>> response, String requestBody) {
        HttpStatusCode statusCode = response.getStatusCode();

        try {
            SheInResponse<SheInProductInsertResponseDto> sheInResponse = super.handleResponse(response);

            if (statusCode.is2xxSuccessful() && sheInResponse.getInfo() != null && sheInResponse.getInfo().success()){
                return handleSuccess(siteName, productGroupId, sheInResponse.getInfo(), requestBody, statusCode.value());
            }else{
                String errorMessage = sheInResponse.getInfo().preValidResult().stream()
                        .map(SheInProductInsertResponseDto.PreValidResult::getErrorMessage)
                        .collect(Collectors.joining("\n"));

                return handleFailure(siteName, productGroupId, errorMessage, requestBody, 400);
            }

        } catch (ResponseStatusException e) {
            return handleFailure(siteName, productGroupId, e.getReason(), requestBody, e.getStatusCode().value());
        }

    }

    private SyncResult handleSuccess(SiteName siteName, long productGroupId, SheInProductInsertResponseDto info, String requestBody, int statusCode) {
        return new SyncResult(
                siteName,
                statusCode,
                true,
                SyncStatus.REVIEW,
                "Product registered successfully",
                productGroupId,
                info.spuName(),
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
                requestBody
        );
    }


}
