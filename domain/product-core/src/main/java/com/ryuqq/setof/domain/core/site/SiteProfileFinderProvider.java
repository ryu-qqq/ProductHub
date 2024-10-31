package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.core.SiteType;
import org.springframework.stereotype.Component;
import provider.AbstractProvider;

import java.util.List;

@Component
public class SiteProfileFinderProvider extends AbstractProvider<SiteType, SiteProfileFinder> {

    public SiteProfileFinderProvider(List<SiteProfileFinder> services) {
        for (SiteProfileFinder service : services) {
            map.put(service.getSiteType(), service);
        }
    }

}
