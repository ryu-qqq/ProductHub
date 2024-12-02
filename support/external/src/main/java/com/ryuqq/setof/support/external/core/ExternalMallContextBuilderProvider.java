package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.utils.AbstractProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalMallContextBuilderProvider extends AbstractProvider<SiteName, ExternalMallContextBuilder> {

    public ExternalMallContextBuilderProvider(List<ExternalMallContextBuilder> providers) {
        for (ExternalMallContextBuilder provider : providers) {
            map.put(provider.getSiteName(), provider);
        }
    }

}
