package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.utils.AbstractProvider;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ExternalMallContextMapperProvider extends AbstractProvider<SiteName, ExternalMallContextMapper> {

    public ExternalMallContextMapperProvider(List<ExternalMallContextMapper> mappers) {
        for (ExternalMallContextMapper mapper : mappers) {
            map.put(mapper.getSiteName(), mapper);
        }
    }

}
