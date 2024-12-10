package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.utils.AbstractProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalMallProductRegistrationServiceProvider extends AbstractProvider<SiteName, ExternalMallProductRegistrationService> {

    public ExternalMallProductRegistrationServiceProvider(List<ExternalMallProductRegistrationService> mappers) {
        for (ExternalMallProductRegistrationService mapper : mappers) {
            map.put(mapper.getSiteName(), mapper);
        }
    }

}
