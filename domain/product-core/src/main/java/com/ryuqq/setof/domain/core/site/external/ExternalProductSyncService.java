package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.TraceIdHolder;
import com.ryuqq.setof.domain.core.site.external.mapper.buyma.BuyMaProductMapper;
import com.ryuqq.setof.enums.core.EntityType;
import com.ryuqq.setof.enums.core.RequestType;
import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.storage.db.core.site.external.ExternalRequestEntity;
import com.ryuqq.setof.storage.db.core.site.external.ExternalRequestPersistenceRepository;
import com.ryuqq.setof.support.external.core.buyma.BuyMaClient;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaProduct;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaProductContext;
import com.ryuqq.setof.support.utils.JsonUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ExternalProductSyncService {

    private final ExternalProductDataFinder externalProductDataFinder;
    private final ExternalProductCommandService externalProductCommandService;
    private final ExternalRequestPersistenceRepository externalRequestPersistenceRepository;
    private final BuyMaProductMapper buyMaProductMapper;
    private final BuyMaClient buyMaClient;

    public ExternalProductSyncService(ExternalProductDataFinder externalProductDataFinder, ExternalProductCommandService externalProductCommandService, ExternalRequestPersistenceRepository externalRequestPersistenceRepository, BuyMaProductMapper buyMaProductMapper, BuyMaClient buyMaClient) {
        this.externalProductDataFinder = externalProductDataFinder;
        this.externalProductCommandService = externalProductCommandService;
        this.externalRequestPersistenceRepository = externalRequestPersistenceRepository;
        this.buyMaProductMapper = buyMaProductMapper;
        this.buyMaClient = buyMaClient;
    }


    @Transactional
    public void temp(long siteId, long sellerId){
        ExternalProductFilter externalProductFilter = new ExternalProductFilter(siteId, sellerId, SyncStatus.REVIEW, null, 1000, List.of());
        List<ExternalProductUploadData> externalProductUploadData = externalProductDataFinder.findExternalProductUploadData(externalProductFilter);

        List<Long> failProductGroupIds = new ArrayList<>();
        List<ExternalRequestContext> productGroupContexts = new ArrayList<>();

        for (ExternalProductUploadData ep : externalProductUploadData) {
            BuyMaProductContext buyMaProductContext = null;
            try {

                BuyMaProduct buyMaProducts = buyMaProductMapper.toBuyMaProducts(ep);
                buyMaProductContext = new BuyMaProductContext(buyMaProducts);
                Map<String, Object> stringObjectMap = buyMaClient.sendProduct(buyMaProductContext);
                System.out.println("stringObjectMap = " + stringObjectMap);
                productGroupContexts.add(new ExternalRequestContext(ep.externalProductContext().getProductGroupId(), true, buyMaProductContext));


            } catch (Exception e) {

                productGroupContexts.add(new ExternalRequestContext(ep.externalProductContext().getProductGroupId(), false, buyMaProductContext));
                failProductGroupIds.add(ep.externalProductContext().getProductGroupId());
            }
        }

        if(!failProductGroupIds.isEmpty()){
            externalProductCommandService.updateStatusByProductGroupId(failProductGroupIds, SyncStatus.FAILED);
        }

        if(!productGroupContexts.isEmpty()){
            List<ExternalRequestEntity> externalRequestEntities = productGroupContexts.stream()
                    .map(buyMaProductContext ->
                            new ExternalRequestEntity(
                                    TraceIdHolder.getTraceId(),
                                    RequestType.PRODUCT_REGISTER,
                                    4L,
                                    EntityType.PRODUCT,
                                    buyMaProductContext.getProductGroupId(),
                                    SyncStatus.PROCESSING,
                                    JsonUtils.toJson(buyMaProductContext.getBuyMaProductContext())
                            ))
                    .toList();
            externalRequestPersistenceRepository.saveAllExternalRequest(externalRequestEntities);
        }

    }

}
