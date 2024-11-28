package com.ryuqq.setof.domain.core.site.external.mapper;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.utils.AbstractProvider;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ExternalProductMapperProvider extends AbstractProvider<SiteName, ExternalProductMapper> {

    public ExternalProductMapperProvider(List<ExternalProductMapper> mappers) {
        for (ExternalProductMapper mapper : mappers) {
            map.put(mapper.getSiteName(), mapper);
        }
    }


}
