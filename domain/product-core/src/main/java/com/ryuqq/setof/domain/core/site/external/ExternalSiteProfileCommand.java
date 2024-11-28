package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.site.SiteProfileCommand;
import com.ryuqq.setof.storage.db.core.site.external.ExternalPolicyEntity;

public record ExternalSiteProfileCommand(
        String name,
        String description,
        ExternalSiteProductPolicyCommand externalSiteProductPolicyCommand,
        ExternalSitePricePolicyCommand externalSitePricePolicyCommand
) implements SiteProfileCommand {

    public ExternalPolicyEntity toEntity(long siteId){
        return new ExternalPolicyEntity(siteId, name, description, true);
    }



}
