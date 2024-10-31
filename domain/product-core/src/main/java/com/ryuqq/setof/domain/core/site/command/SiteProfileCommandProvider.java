package com.ryuqq.setof.domain.core.site.command;

import com.ryuqq.setof.core.SiteType;
import org.springframework.stereotype.Component;
import provider.AbstractProvider;

import java.util.List;

@Component
public class SiteProfileCommandProvider extends AbstractProvider<SiteType, SiteProfileCommandService<? extends SiteProfileCommand>> {

    public SiteProfileCommandProvider(List<SiteProfileCommandService<? extends SiteProfileCommand>> services) {
        for (SiteProfileCommandService<? extends SiteProfileCommand> service : services) {
            map.put(service.getSiteType(), service);
        }
    }

}
