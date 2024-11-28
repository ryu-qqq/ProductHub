package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.domain.core.site.external.ExternalPolicyContextFinder;
import com.ryuqq.setof.domain.core.site.external.ExternalProductPolicy;
import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupNameConfigDto;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupConfigPersistenceRepository;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupNameConfigEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductGroupNameCommandService {

    private final ProductGroupConfigFinder productGroupConfigFinder;
    private final ProductGroupConfigPersistenceRepository productGroupConfigPersistenceRepository;
    private final ExternalPolicyContextFinder externalPolicyContextFinder;


    public ProductGroupNameCommandService(ProductGroupConfigFinder productGroupConfigFinder, ProductGroupConfigPersistenceRepository productGroupConfigPersistenceRepository, ExternalPolicyContextFinder externalPolicyContextFinder) {
        this.productGroupConfigFinder = productGroupConfigFinder;
        this.productGroupConfigPersistenceRepository = productGroupConfigPersistenceRepository;
        this.externalPolicyContextFinder = externalPolicyContextFinder;
    }

    public void insertAll(List<Long> siteIds, List<Long> productGroupIds) {
        Map<Long, ExternalProductPolicy> siteIdMap = toSiteIdMap(siteIds);
        Map<Long, ProductGroupConfig> productGroupIdMap = toProductGroupIdMap(productGroupIds);

        Set<ProductGroupNameConfigEntity> nameConfigEntities = new HashSet<>();


        for(Long siteId : siteIds) {
            ExternalProductPolicy policy = siteIdMap.get(siteId);

            if(policy != null){
                for (Long productGroupId : productGroupIds) {
                    ProductGroupConfig config = productGroupIdMap.get(productGroupId);
                    if (config == null) continue;
                    if (policy.translated()) {
                        nameConfigEntities.add(new ProductGroupNameConfigEntity(config.getConfigId(), policy.countryCode(), "", false));
                    }
                }
            }
        }

        if(!nameConfigEntities.isEmpty()){
            productGroupConfigPersistenceRepository.saveAllProductGroupNameConfigEntities(new ArrayList<>(nameConfigEntities));
        }
    }

    public void updateAll(List<ProductGroupNameCommand> productGroupNameCommands){
        List<ProductGroupNameConfigDto> productGroupNameConfigDtos = productGroupNameCommands.stream().map(ProductGroupNameCommand::toProductGroupNameConfigDto).toList();
        productGroupConfigPersistenceRepository.updateAllProductGroupNameConfigEntities(productGroupNameConfigDtos);
    }



    private Map<Long, ExternalProductPolicy> toSiteIdMap(List<Long> siteIds) {
        return externalPolicyContextFinder.findExternalProductPolicies(siteIds).stream()
                .collect(Collectors.toMap(ExternalProductPolicy::siteId, Function.identity()));
    }


    private Map<Long, ProductGroupConfig> toProductGroupIdMap(List<Long> productGroupIds) {
        return productGroupConfigFinder.fetchByConfigIds(productGroupIds).stream()
                .collect(Collectors.toMap(ProductGroupConfig::getProductGroupId, Function.identity()));
    }


}
