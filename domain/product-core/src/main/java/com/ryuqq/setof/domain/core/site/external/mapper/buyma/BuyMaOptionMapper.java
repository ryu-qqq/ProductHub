package com.ryuqq.setof.domain.core.site.external.mapper.buyma;

import com.ryuqq.setof.domain.core.category.MappingCategory;
import com.ryuqq.setof.domain.core.product.Option;
import com.ryuqq.setof.domain.core.product.Product;
import com.ryuqq.setof.domain.core.product.gpt.OptionsResult;
import com.ryuqq.setof.domain.core.site.external.*;
import com.ryuqq.setof.enums.core.ProductDataType;
import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaOption;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaVariant;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaVariantContext;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaVariantOption;
import com.ryuqq.setof.support.utils.JsonUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class BuyMaOptionMapper {

    private final ExternalCategoryOptionFinder externalCategoryOptionFinder;
    private final ExternalProductProcessingResultQueryService externalProductProcessingResultQueryService;

    public BuyMaOptionMapper(ExternalCategoryOptionFinder externalCategoryOptionFinder, ExternalProductProcessingResultQueryService externalProductProcessingResultQueryService) {
        this.externalCategoryOptionFinder = externalCategoryOptionFinder;
        this.externalProductProcessingResultQueryService = externalProductProcessingResultQueryService;
    }

    public BuyMaVariantContext getVariants(long productGroupId, MappingCategory mappingCategory, List<Product> products) {
        List<ExternalCategoryOption> categoryOptions = getCategoryOptions(Long.parseLong(mappingCategory.externalCategoryId()));
        if (categoryOptions.isEmpty()) {
            return createNoOptionContext(products);
        }
        return createOptionContext(productGroupId, categoryOptions, products);
    }

    private List<ExternalCategoryOption> getCategoryOptions(long categoryId) {
        return externalCategoryOptionFinder.findExternalCategoryOptions(SiteName.BUYMA, categoryId);
    }

    private BuyMaVariantContext createNoOptionContext(List<Product> products) {
        List<BuyMaOption> options = List.of(
                new BuyMaOption("color", "multicolor", 1, 99),
                new BuyMaOption("size", "FREE", 1, 0)
        );

        List<BuyMaVariantOption> variantOptions = List.of(
                new BuyMaVariantOption("color", "multicolor"),
                new BuyMaVariantOption("size", "FREE")
        );

        int totalQuantity = Math.min( calculateTotalQuantity(products), 50);
        String stockType = totalQuantity > 0 ? "stock_in_hand" : "out_of_stock";

        List<BuyMaVariant> variants = List.of(new BuyMaVariant(variantOptions, stockType, totalQuantity));

        return new BuyMaVariantContext(variants, options, "FREE SIZE", true);
    }

    private BuyMaVariantContext createOptionContext(long productGroupId, List<ExternalCategoryOption> categoryOptions, List<Product> products) {
        List<BuyMaOption> options = new ArrayList<>();
        options.add(new BuyMaOption("color", "MultiColor", 1, 99));

        List<BuyMaVariant> variants = new ArrayList<>();

        Optional<OptionsResult> optionsResultOpt = getOptionsResult(productGroupId);

        optionsResultOpt.ifPresentOrElse(
                optionsResult -> populateOptionsWithProductSizes(options, categoryOptions, products, variants),
                () -> populateOptionsWithProductSizes(options, categoryOptions, products, variants)
        );

        return new BuyMaVariantContext(variants, options, "For more information, please contact us", variants.isEmpty());
    }

    private Optional<OptionsResult> getOptionsResult(long productGroupId) {
        return externalProductProcessingResultQueryService
                .findExternalProductProcessing(productGroupId, ProductDataType.OPTIONS)
                .map(result -> JsonUtils.fromJson(result.result(), OptionsResult.class));
    }

    private void populateOptionsWithNormalizedSizes(List<BuyMaOption> options, List<ExternalCategoryOption> categoryOptions, OptionsResult optionsResult) {
        List<String> sizes = optionsResult.normalizedOptions().sizes();
        if (sizes != null) {
            int position = 1;
            for (String size : sizes) {
                long masterId = findMatchingOptionId(size, categoryOptions);
                validateMasterId(masterId, size);
                options.add(new BuyMaOption("size", size, position++, masterId));
            }
        }
    }

    private void populateOptionsWithProductSizes(
            List<BuyMaOption> options,
            List<ExternalCategoryOption> categoryOptions,
            List<Product> products,
            List<BuyMaVariant> variants
    ) {
        // masterId 기준으로 옵션과 재고를 통합
        Map<Long, Integer> masterIdToStock = new HashMap<>();
        Map<Long, String> masterIdToOptionValue = new HashMap<>();

        // 모든 옵션 처리
        for (Product product : products) {
            for (Option productOption : product.getOptions()) {
                if (productOption.getOptionName().isSize() || productOption.getOptionName().isDefaultOne() || productOption.getOptionName().isDefaultTwo()) {
                    String optionValue = productOption.getOptionValue();
                    long masterId = findMatchingOptionId(optionValue, categoryOptions);

                    if (masterId != 0L) {
                        // 재고 통합
                        masterIdToStock.merge(masterId, product.getQuantity(), Integer::sum);
                        masterIdToOptionValue.putIfAbsent(masterId, optionValue);
                    }
                }
            }
        }

        // 옵션 배열 생성
        int position = 1;
        for (Map.Entry<Long, String> entry : masterIdToOptionValue.entrySet()) {
            long masterId = entry.getKey();
            String optionValue = entry.getValue();
            options.add(new BuyMaOption("size", optionValue, position++, masterId));
        }

        // Variant 배열 생성
        for (Map.Entry<Long, Integer> entry : masterIdToStock.entrySet()) {
            long masterId = entry.getKey();
            int totalStock = entry.getValue();
            String optionValue = masterIdToOptionValue.get(masterId);

            String stockType = totalStock > 0 ? "stock_in_hand" : "out_of_stock";
            variants.add(new BuyMaVariant(
                    List.of(
                            new BuyMaVariantOption("color", "MultiColor"),
                            new BuyMaVariantOption("size", optionValue)
                    ),
                    stockType,
                    Math.min(totalStock, 50)
            ));
        }
    }

    private int calculateTotalQuantity(List<Product> products) {
        return products.stream()
                .map(Product::getQuantity)
                .reduce(0, Integer::sum);
    }

    private long findMatchingOptionId(String size, List<ExternalCategoryOption> categoryOptions) {
        return categoryOptions.stream()
                .filter(option -> matchesRange(size, option.optionValue()))
                .map(ExternalCategoryOption::optionId)
                .findFirst()
                .orElse(0L);
    }

    private boolean matchesRange(String size, String externalValue) {
        try {
            if (externalValue == null || externalValue.isEmpty()) {
                // 외부 값이 없으면 매칭 실패로 간주
                return false;
            }

            if (isTextOnly(size) && isTextOnly(externalValue)) {
                return size.equals(externalValue) || externalValue.endsWith("以下") && size.equals(externalValue.replace("以下", "")) || externalValue.endsWith("以上") && size.equals(externalValue.replace("以上", ""));
            }

            Double internalSize = extractNumericPart(size);
            Double externalSize = extractNumericPart(externalValue);
            String condition = extractCondition(externalValue);

            if (internalSize != null && externalSize != null) {
                if ("以下".equals(condition)) {
                    return internalSize <= externalSize * 10; // cm to mm 변환
                } else if ("以上".equals(condition)) {
                    return internalSize >= externalSize * 10; // cm to mm 변환
                } else {
                    return internalSize.equals(externalSize * 10); // 정확히 매칭
                }
            }
            return false;
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    String.format("Invalid format for size: %s or externalValue: %s", size, externalValue), e);
        }
    }

    private boolean isTextOnly(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        return !value.matches(".*\\d.*");
    }

    private String extractCondition(String value) {
        if (value.contains("以下")) return "以下";
        if (value.contains("以上")) return "以上";
        return "";
    }

    private Double extractNumericPart(String value) {
        try {
            // 정규식을 사용하여 숫자와 단위를 추출
            Pattern pattern = Pattern.compile("([0-9.]+)(cm|mm)");
            Matcher matcher = pattern.matcher(value);

            if (matcher.find()) {
                String numeric = matcher.group(1); // 숫자 부분 추출
                String unit = matcher.group(2); // 단위 추출

                // 숫자 변환 및 범위 확인
                double size = Double.parseDouble(numeric);
                if (unit.equals("cm") && size >= 20 && size <= 40) { // cm 범위
                    return size;
                } else if (unit.equals("mm") && size >= 200 && size <= 400) { // mm 범위
                    return size;
                }
            }
            return null; // 유효하지 않은 경우 null 반환
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void validateMasterId(long masterId, String size) {
        if (masterId == 0L) {
            throw new IllegalArgumentException(String.format("No matching masterId found for size: %s", size));
        }
    }
}
