package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.utils.AbstractProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalMallProductRegistrationServiceProvider extends AbstractProvider<SiteName, ExternalMallProductRegistrationService<? extends ExternalMallProductPayload>> {

    public ExternalMallProductRegistrationServiceProvider(List<ExternalMallProductRegistrationService<? extends ExternalMallProductPayload>> mappers) {
        for (ExternalMallProductRegistrationService<? extends ExternalMallProductPayload> mapper : mappers) {
            map.put(mapper.getSiteName(), mapper);
        }
    }

}
