package com.ryuqq.setof.api.core.controller.v1.site.service;

import com.ryuqq.setof.api.core.controller.v1.site.request.ExternalProductIntegrationRequestDto;
import com.ryuqq.setof.domain.core.site.external.ExternalMallIntegrationManager;
import org.springframework.stereotype.Service;

@Service
public class ExternalProductIntegrationService {

    private final ExternalMallIntegrationManager externalMallIntegrationManager;

    public ExternalProductIntegrationService(ExternalMallIntegrationManager externalMallIntegrationManager) {
        this.externalMallIntegrationManager = externalMallIntegrationManager;
    }


    public Integer integration(ExternalProductIntegrationRequestDto externalProductIntegrationRequestDto){
        return externalMallIntegrationManager.integration(externalProductIntegrationRequestDto.siteId(), externalProductIntegrationRequestDto.sellerId(), externalProductIntegrationRequestDto.status(), externalProductIntegrationRequestDto.pageSize());
    }

}
