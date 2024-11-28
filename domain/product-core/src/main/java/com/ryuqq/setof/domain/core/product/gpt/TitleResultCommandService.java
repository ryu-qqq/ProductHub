package com.ryuqq.setof.domain.core.product.gpt;

import com.ryuqq.setof.domain.core.product.*;
import com.ryuqq.setof.domain.core.site.external.ExternalProductCommandService;
import com.ryuqq.setof.domain.core.site.external.ExternalProductName;
import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.enums.core.ProductDataType;
import com.ryuqq.setof.storage.db.core.product.group.ProductColorEntity;
import com.ryuqq.setof.storage.db.core.product.group.ProductColorPersistenceRepository;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupPersistenceRepository;
import com.ryuqq.setof.storage.db.core.site.external.ExternalProcessingResultPersistenceRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TitleResultCommandService extends AbstractBatchResultCommandService<TitleResult> {

    private final ProductGroupConfigFinder productGroupConfigFinder;
    private final ProductGroupContextQueryService productGroupContextQueryService;
    private final ProductGroupNameCommandService productGroupNameCommandService;
    private final ProductColorPersistenceRepository productColorPersistenceRepository;

    protected TitleResultCommandService(ExternalProcessingResultPersistenceRepository externalProcessingResultPersistenceRepository, ExternalProductCommandService externalProductPersistenceService, ProductGroupPersistenceRepository productGroupPersistenceRepository, ProductGroupConfigFinder productGroupConfigFinder, ProductGroupContextQueryService productGroupContextQueryService, ProductGroupNameCommandService productGroupNameCommandService, ProductColorPersistenceRepository productColorPersistenceRepository) {
        super(externalProcessingResultPersistenceRepository, externalProductPersistenceService, productGroupPersistenceRepository);
        this.productGroupConfigFinder = productGroupConfigFinder;
        this.productGroupContextQueryService = productGroupContextQueryService;
        this.productGroupNameCommandService = productGroupNameCommandService;
        this.productColorPersistenceRepository = productColorPersistenceRepository;
    }

    @Override
    public void execute(TitleResult titleResult) {
        doAdditionalExecute(List.of(titleResult));
        saveExternalProductResult(toEntity(titleResult));
        updateProductGroupAndExternalProductReviewStatus(List.of(titleResult));
    }

    @Override
    public void execute(List<TitleResult> titleResults) {
        doAdditionalExecute(titleResults);
        saveExternalProductResults(toEntities(titleResults));
        updateProductGroupAndExternalProductReviewStatus(titleResults);

    }

    public void doAdditionalExecute(List<TitleResult> titleResults) {
//        List<Long> productGroupIds = getProductGroupIds(titleResults);
//        Map<Long, TitleResult> productGroupIdTitleMap = toProductGroupIdTitleMap(titleResults);
//        Map<Long, ProductGroup> productGroupIdMap = toProductGroupIdMap(productGroupIds);
//        Map<Long, List<ProductGroupNameConfig>> productGroupIdConfigList = toProductGroupIdConfigList(productGroupIds);
//
//        List<ProductStyleCodeCommand> styleCodeCommands = processStyleCodeUpdates(productGroupIdMap, productGroupIdTitleMap);
//        List<ProductColorEntity> colorEntities = processColorUpdates(productGroupIdTitleMap);
//        List<ProductGroupNameCommand> nameCommands = processNameUpdates(productGroupIdConfigList, productGroupIdTitleMap);
//
//        applyUpdates(styleCodeCommands, colorEntities, nameCommands);
    }

    private List<ProductStyleCodeCommand> processStyleCodeUpdates(Map<Long, ProductGroup> productGroupIdMap, Map<Long, TitleResult> productGroupIdTitleMap) {
        List<ProductStyleCodeCommand> commands = new ArrayList<>();
        for (Map.Entry<Long, TitleResult> entry : productGroupIdTitleMap.entrySet()) {
            Long productGroupId = entry.getKey();
            TitleResult titleResult = entry.getValue();
            ProductGroup productGroup = productGroupIdMap.get(productGroupId);

            if (productGroup != null && StringUtils.hasText(titleResult.styleCode())
                    && !Objects.equals(productGroup.styleCode(), titleResult.styleCode())) {
                commands.add(new ProductStyleCodeCommand(productGroup.productGroupId(), titleResult.styleCode()));
            }
        }
        return commands;
    }

    private List<ProductColorEntity> processColorUpdates(Map<Long, TitleResult> productGroupIdTitleMap) {
        List<ProductColorEntity> entities = new ArrayList<>();
        for (Map.Entry<Long, TitleResult> entry : productGroupIdTitleMap.entrySet()) {
            Long productGroupId = entry.getKey();
            TitleResult titleResult = entry.getValue();

            if (StringUtils.hasText(titleResult.colorInTitle())) {
                entities.add(new ProductColorEntity(
                        productGroupId,
                        null,
                        titleResult.colorInTitle(),
                        0.0
                ));
            }
        }
        return entities;
    }

    private List<ProductGroupNameCommand> processNameUpdates(Map<Long, List<ProductGroupNameConfig>> productGroupIdConfigList, Map<Long, TitleResult> productGroupIdTitleMap) {
        List<ProductGroupNameCommand> commands = new ArrayList<>();
        for (Map.Entry<Long, TitleResult> entry : productGroupIdTitleMap.entrySet()) {
            Long productGroupId = entry.getKey();
            TitleResult titleResult = entry.getValue();
            List<ProductGroupNameConfig> productGroupNameConfigs = productGroupIdConfigList.getOrDefault(productGroupId, List.of());

            Map<Origin, ProductGroupNameConfig> currentNameMap = productGroupNameConfigs.stream()
                    .collect(Collectors.toMap(ProductGroupNameConfig::countryCode, Function.identity(), (v1, v2) -> v2));

            for (ExternalProductName externalName : titleResult.externalProductNames()) {
                ProductGroupNameConfig currentConfig = currentNameMap.get(externalName.countryCode());
                if (currentConfig != null) {
                    commands.add(new ProductGroupNameCommand(
                            currentConfig.productGroupNameConfigId(),
                            externalName.productName()
                    ));
                }
            }
        }
        return commands;
    }

//    private void applyUpdates(List<ProductStyleCodeCommand> styleCodeCommands, List<ProductColorEntity> colorEntities, List<ProductGroupNameCommand> nameCommands) {
//        if (!styleCodeCommands.isEmpty()) {
//            getProductGroupPersistenceService().updateStyleCodes(
//                    styleCodeCommands.stream()
//                            .map(ProductStyleCodeCommand::toProductStyleCodeDto)
//                            .toList()
//            );
//        }
//
//        if (!colorEntities.isEmpty()) {
//            productColorPersistenceRepository.saveAllProductColorEntities(colorEntities);
//        }
//
//        if (!nameCommands.isEmpty()) {
//            productGroupNameCommandService.updateAll(nameCommands);
//        }
//    }
//
//    private Map<Long, TitleResult> toProductGroupIdTitleMap(List<TitleResult> titleResults) {
//        return titleResults.stream()
//                .collect(Collectors.toMap(TitleResult::getProductGroupId, Function.identity(), (v1, v2) -> v2));
//    }
//
//
//    private Map<Long, ProductGroup> toProductGroupIdMap(List<Long> productGroupIds) {
//        return null;
////        return productGroupContextQueryService.findProductGroups(productGroupIds).stream()
////                .collect(Collectors.toMap(ProductGroup::productGroupId, Function.identity(), (v1, v2) -> v2));
//    }
//
//    private Map<Long, List<ProductGroupNameConfig>> toProductGroupIdConfigList(List<Long> productGroupIds){
//        return productGroupConfigFinder.findProductGroupNameConfigs(productGroupIds).stream()
//                .collect(Collectors.groupingBy(ProductGroupNameConfig::productGroupId));
//    }

    @Override
    public ProductDataType getProductDataType() {
        return ProductDataType.TITLE;
    }
}
