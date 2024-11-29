package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.enums.core.SiteType;
import org.springframework.stereotype.Component;
import com.ryuqq.setof.support.utils.AbstractProvider;

import java.util.List;

@Component
public class SiteProfileCommandProvider extends AbstractProvider<SiteType, SiteProfileCommandService<? extends SiteProfileCommand>> {

    public SiteProfileCommandProvider(List<SiteProfileCommandService<? extends SiteProfileCommand>> services) {
        for (SiteProfileCommandService<? extends SiteProfileCommand> service : services) {
            map.put(service.getSiteType(), service);
        }
    }

}
