package com.ryuqq.setof.domain.core.site.external;
import com.ryuqq.setof.db.core.site.external.ExternalSiteSellerEntity;
import com.ryuqq.setof.db.core.site.external.ExternalSiteSellerPersistenceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalSiteSellerRelationCommandService {

    private final ExternalSiteSellerRelationFinder externalSiteSellerRelationFinder;
    private final ExternalSiteSellerPersistenceRepository externalSiteSellerPersistenceRepository;

    public ExternalSiteSellerRelationCommandService(ExternalSiteSellerRelationFinder externalSiteSellerRelationFinder, ExternalSiteSellerPersistenceRepository externalSiteSellerPersistenceRepository) {
        this.externalSiteSellerRelationFinder = externalSiteSellerRelationFinder;
        this.externalSiteSellerPersistenceRepository = externalSiteSellerPersistenceRepository;
    }

    @Transactional
    public int inserts(ExternalSiteSellerRelationCommand externalSiteSellerRelationCommand) {
        List<ExternalSiteSellerRelation> existingRelations = externalSiteSellerRelationFinder.findExternalSiteSellerRelation(
                List.of(externalSiteSellerRelationCommand.sellerId())
        );

        List<Long> existingSiteIds = existingRelations.stream()
                .flatMap(e -> e.externalSiteProductPolicies().stream())
                .map(ExternalProductPolicy::siteId)
                .toList();

        List<ExternalSiteSellerEntity> externalSiteSellerEntities = externalSiteSellerRelationCommand.toExternalSiteSellerEntities()
                .stream()
                .filter(entity -> !existingSiteIds.contains(entity.getSiteId()))
                .toList();

        if (!externalSiteSellerEntities.isEmpty()) {
            externalSiteSellerPersistenceRepository.inserts(externalSiteSellerEntities);
        }

        return externalSiteSellerEntities.size();
    }

}
