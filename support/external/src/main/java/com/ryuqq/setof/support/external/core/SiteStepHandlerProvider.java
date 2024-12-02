package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.utils.AbstractProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SiteStepHandlerProvider extends AbstractProvider<SiteName, List<SyncStepHandler>>{

    public SiteStepHandlerProvider(SiteStepHandlerFactory siteStepHandlerFactory) {
        for (SiteName siteName : SiteName.getExternalSyncSite()) {
            try {
                map.put(siteName, siteStepHandlerFactory.createHandlers(siteName));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException ("Skipping unsupported site: " + siteName);
            }
        }
    }

}
