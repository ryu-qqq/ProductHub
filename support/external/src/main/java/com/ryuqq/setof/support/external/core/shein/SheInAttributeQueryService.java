package com.ryuqq.setof.support.external.core.shein;

import com.ryuqq.setof.support.external.core.ExternalSyncCategoryOptionCommand;
import com.ryuqq.setof.support.external.core.dto.SheInAttributeRequestDto;
import com.ryuqq.setof.support.external.core.dto.SheInAttributeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SheInAttributeQueryService {

    private final static String SIZE = "Size";
    private final SheInClient sheInClient;


    public SheInAttributeQueryService(SheInClient sheInClient) {
        this.sheInClient = sheInClient;
    }

    public List<ExternalSyncCategoryOptionCommand> fetchSheInAttributes(long extraCategoryId) {
        SheInAttributeRequestDto sheInAttributeRequestDto = new SheInAttributeRequestDto(List.of(extraCategoryId));

        ResponseEntity<SheInResponse<SheInAttributeResponse>> response = sheInClient.fetchAttributes(sheInAttributeRequestDto);

        if (response.getStatusCode().is2xxSuccessful()) {
            SheInResponse<SheInAttributeResponse> body = response.getBody();
            if (body != null) {
               SheInAttributeResponse attributeResponses = body.getInfo();
                return attributeResponses.data().stream()
                        .flatMap(attribute -> attribute.attributeInfos().stream()
                                .filter(attributeInfo -> SIZE.equals(attributeInfo.attributeName()))
                                .flatMap(attributeInfo -> attributeInfo.attributeValueInfos().stream()
                                        .map(attributeValueInfo -> new ExternalSyncCategoryOptionCommand(
                                                attribute.productTypeId(),
                                                attributeInfo.attributeId(),
                                                attributeValueInfo.attributeValueId(),
                                                attributeValueInfo.attributeValueEn()
                                        )))).distinct()
                        .toList();
            }
        }

        return List.of();
    }

}
