package com.ryuqq.setof.support.external.core.shein;

import com.ryuqq.setof.support.external.core.ExternalSyncCategoryOptionCommand;
import com.ryuqq.setof.support.external.core.shein.dto.SheInAttributeRequestDto;
import com.ryuqq.setof.support.external.core.shein.dto.SheInAttributeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SheInAttributeQueryService {

    private final static String SIZE = "Size";
    private final SheInClient sheInClient;


    public SheInAttributeQueryService(SheInClient sheInClient) {
        this.sheInClient = sheInClient;
    }

    public List<ExternalSyncCategoryOptionCommand> fetchSheInAttributes(long extraCategoryId) {
        SheInAttributeRequestDto sheInAttributeRequestDto = new SheInAttributeRequestDto(List.of(extraCategoryId));

        ResponseEntity<SheInResponse<List<SheInAttributeResponse>>> response = sheInClient.fetchAttributes(sheInAttributeRequestDto);

        if (response.getStatusCode().is2xxSuccessful()) {
            SheInResponse<List<SheInAttributeResponse>> body = response.getBody();
            if (body != null) {
                List<SheInAttributeResponse> attributeResponses = body.getInfo().data();
                return attributeResponses.stream()
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
