package com.ryuqq.setof.domain.core.product.gpt;

import com.ryuqq.setof.domain.core.product.*;
import com.ryuqq.setof.domain.core.site.external.ExternalProductName;
import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.enums.core.ProductDataType;
import com.ryuqq.setof.db.core.product.gpt.ProductProcessingResultPersistenceRepository;
import com.ryuqq.setof.db.core.product.group.ProductColorEntity;
import com.ryuqq.setof.db.core.product.group.ProductColorPersistenceRepository;
import com.ryuqq.setof.db.core.product.group.ProductGroupPersistenceRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GptTitleResultCommandService extends AbstractGptBatchResultCommandService<GptTitleResult> {

    private final ProductGroupNameConfigFinder productGroupNameConfigFinder;
    private final ProductGroupFinder productGroupFinder;
    private final ProductGroupNameCommandService productGroupNameCommandService;
    private final ProductColorPersistenceRepository productColorPersistenceRepository;

    protected GptTitleResultCommandService(ProductProcessingResultPersistenceRepository productProcessingResultPersistenceRepository, ProductGroupPersistenceRepository productGroupPersistenceRepository, ProductGroupNameConfigFinder productGroupNameConfigFinder, ProductGroupFinder productGroupFinder, ProductGroupNameCommandService productGroupNameCommandService, ProductColorPersistenceRepository productColorPersistenceRepository) {
        super(productProcessingResultPersistenceRepository, productGroupPersistenceRepository);
        this.productGroupNameConfigFinder = productGroupNameConfigFinder;
        this.productGroupFinder = productGroupFinder;
        this.productGroupNameCommandService = productGroupNameCommandService;
        this.productColorPersistenceRepository = productColorPersistenceRepository;
    }


    @Override
    public void execute(GptTitleResult titleResult) {
        doAdditionalExecute(List.of(titleResult));
        saveExternalProductResult(toEntity(titleResult));
        updateProductGroupAndExternalProductReviewStatus(List.of(titleResult));
    }

    @Override
    public void execute(List<GptTitleResult> titleResults) {
        doAdditionalExecute(titleResults);
        saveExternalProductResults(toEntities(titleResults));
        updateProductGroupAndExternalProductReviewStatus(titleResults);

    }

    public void doAdditionalExecute(List<GptTitleResult> titleResults) {
        List<Long> productGroupIds = getProductGroupIds(titleResults);
        Map<Long, GptTitleResult> productGroupIdTitleMap = toProductGroupIdTitleMap(titleResults);
        Map<Long, ProductGroup> productGroupIdMap = toProductGroupIdMap(productGroupIds);
        Map<Long, List<ProductGroupNameConfig>> productGroupIdConfigList = toProductGroupIdConfigList(productGroupIds);

        List<ProductStyleCodeCommand> styleCodeCommands = processStyleCodeUpdates(productGroupIdMap, productGroupIdTitleMap);
        List<ProductColorEntity> colorEntities = processColorUpdates(productGroupIdTitleMap);
        List<ProductGroupNameCommand> nameCommands = processNameUpdates(productGroupIdConfigList, productGroupIdTitleMap);

        applyUpdates(styleCodeCommands, colorEntities, nameCommands);
    }

    private List<ProductStyleCodeCommand> processStyleCodeUpdates(Map<Long, ProductGroup> productGroupIdMap, Map<Long, GptTitleResult> productGroupIdTitleMap) {
        List<ProductStyleCodeCommand> commands = new ArrayList<>();
        for (Map.Entry<Long, GptTitleResult> entry : productGroupIdTitleMap.entrySet()) {
            Long productGroupId = entry.getKey();
            GptTitleResult titleResult = entry.getValue();
            ProductGroup productGroup = productGroupIdMap.get(productGroupId);

            if (productGroup != null && StringUtils.hasText(titleResult.styleCode())
                    && !Objects.equals(productGroup.styleCode(), titleResult.styleCode())) {
                commands.add(new ProductStyleCodeCommand(productGroup.productGroupId(), titleResult.styleCode()));
            }
        }
        return commands;
    }

    private List<ProductColorEntity> processColorUpdates(Map<Long, GptTitleResult> productGroupIdTitleMap) {
        List<ProductColorEntity> entities = new ArrayList<>();
        for (Map.Entry<Long, GptTitleResult> entry : productGroupIdTitleMap.entrySet()) {
            Long productGroupId = entry.getKey();
            GptTitleResult titleResult = entry.getValue();

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

    private List<ProductGroupNameCommand> processNameUpdates(Map<Long, List<ProductGroupNameConfig>> productGroupIdConfigList, Map<Long, GptTitleResult> productGroupIdTitleMap) {
        List<ProductGroupNameCommand> commands = new ArrayList<>();
        for (Map.Entry<Long, GptTitleResult> entry : productGroupIdTitleMap.entrySet()) {
            Long productGroupId = entry.getKey();
            GptTitleResult titleResult = entry.getValue();
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

    private void applyUpdates(List<ProductStyleCodeCommand> styleCodeCommands, List<ProductColorEntity> colorEntities, List<ProductGroupNameCommand> nameCommands) {
        if (!styleCodeCommands.isEmpty()) {
            getProductGroupPersistenceService().updateStyleCodes(
                    styleCodeCommands.stream()
                            .map(ProductStyleCodeCommand::toProductStyleCodeDto)
                            .toList()
            );
        }

        if (!colorEntities.isEmpty()) {
            productColorPersistenceRepository.saveAllProductColorEntities(colorEntities);
        }

        if (!nameCommands.isEmpty()) {
            productGroupNameCommandService.updateAll(nameCommands);
        }
    }

    private Map<Long, GptTitleResult> toProductGroupIdTitleMap(List<GptTitleResult> titleResults) {
        return titleResults.stream()
                .collect(Collectors.toMap(GptTitleResult::getProductGroupId, Function.identity(), (v1, v2) -> v2));
    }


    private Map<Long, ProductGroup> toProductGroupIdMap(List<Long> productGroupIds) {
        return productGroupFinder.fetchProductGroupsByFilter(ProductGroupFilter.of(productGroupIds))
                .stream()
                .collect(Collectors.toMap(ProductGroup::productGroupId, Function.identity(), (v1, v2) -> v2));
    }

    private Map<Long, List<ProductGroupNameConfig>> toProductGroupIdConfigList(List<Long> productGroupIds){
        return productGroupNameConfigFinder.fetchByProductGroupIds(productGroupIds).stream()
                .collect(Collectors.groupingBy(ProductGroupNameConfig::productGroupId));
    }

    @Override
    public ProductDataType getProductDataType() {
        return ProductDataType.TITLE;
    }
}
