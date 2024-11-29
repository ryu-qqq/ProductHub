package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.utils.AbstractProvider;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ExternalSyncProductMapperProvider extends AbstractProvider<SiteName, ExternalSyncProductMapper> {

    public ExternalSyncProductMapperProvider(List<ExternalSyncProductMapper> mappers) {
        for (ExternalSyncProductMapper mapper : mappers) {
            map.put(mapper.getSiteName(), mapper);
        }
    }

}
