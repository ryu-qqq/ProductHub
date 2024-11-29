package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.site.SiteProfileCommandService;
import com.ryuqq.setof.enums.core.SiteType;
import com.ryuqq.setof.storage.db.core.site.external.ExternalPolicyPersistenceRepository;
import com.ryuqq.setof.storage.db.core.site.external.ExternalPricePolicyPersistenceRepository;
import com.ryuqq.setof.storage.db.core.site.external.ExternalPricePolicyRuleEntity;
import com.ryuqq.setof.storage.db.core.site.external.ExternalProductPolicyPersistenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalSiteProfileCommandService  implements SiteProfileCommandService<ExternalSiteProfileCommand> {

    private final ExternalPolicyPersistenceRepository externalPolicyPersistenceRepository;
    private final ExternalProductPolicyPersistenceRepository externalProductPolicyPersistenceRepository;
    private final ExternalPricePolicyPersistenceRepository externalPricePolicyPersistenceService;

    public ExternalSiteProfileCommandService(ExternalPolicyPersistenceRepository externalPolicyPersistenceRepository, ExternalProductPolicyPersistenceRepository externalProductPolicyPersistenceRepository, ExternalPricePolicyPersistenceRepository externalPricePolicyPersistenceService) {
        this.externalPolicyPersistenceRepository = externalPolicyPersistenceRepository;
        this.externalProductPolicyPersistenceRepository = externalProductPolicyPersistenceRepository;
        this.externalPricePolicyPersistenceService = externalPricePolicyPersistenceService;
    }

    @Override
    public SiteType getSiteType() {
        return SiteType.SYNC;
    }

    @Override
    public void insert(long siteId, ExternalSiteProfileCommand externalSiteProfileCommand) {
        long policyId = externalPolicyPersistenceRepository.insert(externalSiteProfileCommand.toEntity(siteId));
        ExternalProductPolicyCommand externalProductPolicyCommand = externalSiteProfileCommand.externalProductPolicyCommand();
        externalProductPolicyPersistenceRepository.insertExternalProductPolicy(externalProductPolicyCommand.toEntity(policyId));

        ExternalPricePolicyCommand externalPricePolicyCommand = externalSiteProfileCommand.externalPricePolicyCommand();
        long pricePolicyId = externalPricePolicyPersistenceService.saveExternalPricePolicy(externalPricePolicyCommand.toEntity(policyId));

        List<ExternalPricePolicyRuleEntity> pricePolicyRuleEntities = externalPricePolicyCommand.externalPricePolicyRuleCommands().stream()
                .map(e -> e.toEntity(pricePolicyId))
                .toList();

        externalPricePolicyPersistenceService.saveAllExternalPricePolicyRule(pricePolicyRuleEntities);
    }


    //Todo 나중에 외부몰 셀러 정책 변경 로직 추가해야함
    @Override
    public void update(long siteId, long policyId, ExternalSiteProfileCommand siteProfileCommand) {

    }


}
