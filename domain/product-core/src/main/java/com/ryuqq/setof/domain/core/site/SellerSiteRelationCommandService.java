package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.storage.db.core.site.ExternalSiteSellerEntity;
import com.ryuqq.setof.storage.db.core.site.ExternalSiteSellerPersistenceService;
import com.ryuqq.setof.storage.db.core.site.dto.SellerSiteRelationDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerSiteRelationCommandService {

    private final ExternalSiteRelationFinder externalSiteRelationFinder;
    private final ExternalSiteSellerPersistenceService externalSiteSellerPersistenceService;

    public SellerSiteRelationCommandService(ExternalSiteRelationFinder externalSiteRelationFinder, ExternalSiteSellerPersistenceService externalSiteSellerPersistenceService) {
        this.externalSiteRelationFinder = externalSiteRelationFinder;
        this.externalSiteSellerPersistenceService = externalSiteSellerPersistenceService;
    }

    @Transactional
    public int inserts(SellerSiteRelationCommand sellerSiteRelationCommand) {
        List<SellerSiteRelationDto> existingRelations = externalSiteRelationFinder.findSellerSiteRelation(
                sellerSiteRelationCommand.siteId(),
                sellerSiteRelationCommand.sellerIds()
        );

        List<Long> existingSellerIds = existingRelations.stream()
                .map(SellerSiteRelationDto::getSellerId)
                .toList();

        List<ExternalSiteSellerEntity> externalSiteSellerEntities = sellerSiteRelationCommand.toExternalSiteSellerEntities()
                .stream()
                .filter(entity -> !existingSellerIds.contains(entity.getSellerId()))
                .toList();

        if (!externalSiteSellerEntities.isEmpty()) {
            externalSiteSellerPersistenceService.inserts(externalSiteSellerEntities);
        }

        return externalSiteSellerEntities.size();
    }

}
