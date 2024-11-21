package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.enums.core.SiteType;
import com.ryuqq.setof.storage.db.core.site.ExternalPricePolicyPersistenceService;
import com.ryuqq.setof.storage.db.core.site.ExternalPricePolicyRuleEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalSiteProfileCommandService  implements SiteProfileCommandService<ExternalSiteProfileCommand>{

    private final ExternalPricePolicyPersistenceService externalPricePolicyPersistenceService;

    public ExternalSiteProfileCommandService(ExternalPricePolicyPersistenceService externalPricePolicyPersistenceService) {
        this.externalPricePolicyPersistenceService = externalPricePolicyPersistenceService;
    }

    @Override
    public SiteType getSiteType() {
        return SiteType.SYNC;
    }

    @Override
    public void insert(long siteId, ExternalSiteProfileCommand externalSiteProfileCommand) {
        ExternalSitePricePolicyCommand externalSitePricePolicyCommand = externalSiteProfileCommand.externalSitePricePolicyCommand();
        long policyId = externalPricePolicyPersistenceService.insert(externalSitePricePolicyCommand.toEntity(siteId));

        List<ExternalPricePolicyRuleEntity> pricePolicyRuleEntities = externalSitePricePolicyCommand.externalSitePricePolicyRuleCommands().stream()
                .map(e -> e.toEntity(policyId))
                .toList();

        externalPricePolicyPersistenceService.insertPolicyRules(pricePolicyRuleEntities);
    }

    @Override
    public void update(long siteId, long mappingId, ExternalSiteProfileCommand siteProfileCommand) {

    }



}
