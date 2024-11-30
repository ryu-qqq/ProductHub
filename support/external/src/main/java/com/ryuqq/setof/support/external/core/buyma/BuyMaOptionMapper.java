package com.ryuqq.setof.support.external.core.buyma;

import com.ryuqq.setof.support.external.core.ExternalMallCategoryOption;
import com.ryuqq.setof.support.external.core.ExternalMallOption;
import com.ryuqq.setof.support.external.core.ExternalMallProduct;
import com.ryuqq.setof.support.external.core.ProcessingOptionResult;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaOption;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaVariant;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaVariantContext;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaVariantOption;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class BuyMaOptionMapper {


    public BuyMaVariantContext getVariants(long productGroupId, List<ExternalMallCategoryOption> categoryOptions, ProcessingOptionResult optionResult, List<ExternalMallProduct> products) {
        if (categoryOptions.isEmpty()) {
            return createNoOptionContext(products);
        }
        return createOptionContext(productGroupId, categoryOptions, optionResult, products);
    }

    private BuyMaVariantContext createNoOptionContext(List<ExternalMallProduct> products) {
        List<BuyMaOption> options = new ArrayList<>();
        List<BuyMaVariantOption> variantOptions = new ArrayList<>();
        options.add(new BuyMaOption("color", "multicolor", 1, 99));

        products.forEach(p ->{
            options.add(new BuyMaOption("size", p.option(), 1, 0));
        });

        products.forEach(p ->{
            variantOptions.add(new BuyMaVariantOption("color", "multicolor"));
            variantOptions.add(new BuyMaVariantOption("size", p.option()));

        });



        int totalQuantity = Math.min( calculateTotalQuantity(products), 50);
        String stockType = totalQuantity > 0 ? "stock_in_hand" : "out_of_stock";

        List<BuyMaVariant> variants = List.of(new BuyMaVariant(variantOptions, stockType, totalQuantity));

        return new BuyMaVariantContext(variants, options, "FREE SIZE", true);
    }

    private BuyMaVariantContext createOptionContext(long productGroupId, List<ExternalMallCategoryOption> categoryOptions, ProcessingOptionResult optionResult, List<ExternalMallProduct> products) {
        List<BuyMaOption> options = new ArrayList<>();
        options.add(new BuyMaOption("color", "MultiColor", 1, 99));

        List<BuyMaVariant> variants = new ArrayList<>();

        Optional<ProcessingOptionResult> optionsResultOpt = Optional.ofNullable(optionResult);

        optionsResultOpt.ifPresentOrElse(
                optionsResult -> populateOptionsWithProductSizes(options, categoryOptions, products, variants),
                () -> populateOptionsWithProductSizes(options, categoryOptions, products, variants)
        );

        return new BuyMaVariantContext(variants, options, "For more information, please contact us", variants.isEmpty());
    }



    private void populateOptionsWithNormalizedSizes(List<BuyMaOption> options, List<ExternalMallCategoryOption> categoryOptions, ProcessingOptionResult optionsResult) {
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
            List<ExternalMallCategoryOption> categoryOptions,
            List<ExternalMallProduct> products,
            List<BuyMaVariant> variants
    ) {

        Map<Long, Integer> masterIdToStock = new HashMap<>();
        Map<Long, String> masterIdToOptionValue = new HashMap<>();

        for (ExternalMallProduct product : products) {
            for (ExternalMallOption productOption : product.options()) {
                if (productOption.optionName().isSize() || productOption.optionName().isDefaultOne() || productOption.optionName().isDefaultTwo()) {
                    String optionValue = productOption.optionValue();
                    long masterId = findMatchingOptionId(optionValue, categoryOptions);

                    if (masterId != 0L) {
                        masterIdToStock.merge(masterId, product.quantity(), Integer::sum);
                        masterIdToOptionValue.putIfAbsent(masterId, optionValue);
                    }
                }
            }
        }


        int position = 1;
        for (Map.Entry<Long, String> entry : masterIdToOptionValue.entrySet()) {
            long masterId = entry.getKey();
            String optionValue = entry.getValue();
            options.add(new BuyMaOption("size", optionValue, position++, masterId));
        }

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

    private int calculateTotalQuantity(List<ExternalMallProduct> products) {
        return products.stream()
                .map(ExternalMallProduct::quantity)
                .reduce(0, Integer::sum);
    }

    private long findMatchingOptionId(String size, List<ExternalMallCategoryOption> categoryOptions) {
        return categoryOptions.stream()
                .filter(option -> matchesRange(size, option.optionValue()))
                .map(ExternalMallCategoryOption::optionId)
                .findFirst()
                .orElse(0L);
    }

    private boolean matchesRange(String size, String externalValue) {
        try {
            if (externalValue == null || externalValue.isEmpty()) {
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
            // 정규식: 숫자와 선택적 단위를 매칭
            Pattern pattern = Pattern.compile("([0-9.]+)(cm|mm)?");
            Matcher matcher = pattern.matcher(value);

            if (matcher.find()) {
                String numeric = matcher.group(1); // 숫자 부분 추출
                String unit = matcher.group(2); // 단위 추출 (null일 수 있음)

                double size = Double.parseDouble(numeric);

                // 단위가 없을 경우 기본 단위를 cm로 가정
                if (unit == null) {
                    if (size >= 20 && size <= 40) { // cm 범위
                        return size;
                    }
                    if (size >= 200 && size <= 400) {
                        return size;
                    }

                } else if (unit.equals("cm") && size >= 20 && size <= 40) { // cm 범위
                    return size;
                } else if (unit.equals("mm") && size >= 200 && size <= 400) { // mm 범위
                    return size;
                }
            }
            return null;
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
