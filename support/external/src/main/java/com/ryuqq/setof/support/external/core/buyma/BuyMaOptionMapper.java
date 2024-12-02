package com.ryuqq.setof.support.external.core.buyma;

import com.ryuqq.setof.support.external.core.ExternalSyncCategoryOption;
import com.ryuqq.setof.support.external.core.ExternalSyncOption;
import com.ryuqq.setof.support.external.core.ExternalSyncProduct;
import com.ryuqq.setof.support.external.core.ExternalSyncOptionResult;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaOption;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaVariant;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaOptionContext;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaVariantOption;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class BuyMaOptionMapper {


    public BuyMaOptionContext getVariants(long productGroupId, List<ExternalSyncCategoryOption> categoryOptions, ExternalSyncOptionResult optionResult, List<ExternalSyncProduct> products) {
        if (categoryOptions.isEmpty()) {
            return createNoOptionContext(products);
        }
        return createOptionContext(productGroupId, categoryOptions, optionResult, products);
    }

    private BuyMaOptionContext createNoOptionContext(List<ExternalSyncProduct> products) {
        List<BuyMaOption> options = List.of(
                new BuyMaOption("color", "multicolor", 1, 99),
                new BuyMaOption("size", "FREE", 1, 0)
        );
        List<BuyMaVariantOption> variantOptions = List.of(
                new BuyMaVariantOption("color", "multicolor"),
                new BuyMaVariantOption("size", "FREE")
        );


        int totalQuantity = Math.min(calculateTotalQuantity(products), 50);
        String stockType = totalQuantity > 0 ? "stock_in_hand" : "out_of_stock";

        List<BuyMaVariant> variants = List.of(new BuyMaVariant(variantOptions, stockType, totalQuantity));

        String optionComment = getOptionComment(products);

        return new BuyMaOptionContext(options, variants, optionComment);
    }

    private BuyMaOptionContext createOptionContext(long productGroupId, List<ExternalSyncCategoryOption> categoryOptions, ExternalSyncOptionResult optionResult, List<ExternalSyncProduct> products) {
        List<BuyMaOption> options = new ArrayList<>();
        options.add(new BuyMaOption("color", "MultiColor", 1, 99));

        List<BuyMaVariant> variants = new ArrayList<>();

        Optional<ExternalSyncOptionResult> optionsResultOpt = Optional.ofNullable(optionResult);

        optionsResultOpt.ifPresentOrElse(
                optionsResult -> populateOptionsWithProductSizes(options, categoryOptions, products, variants),
                () -> populateOptionsWithProductSizes(options, categoryOptions, products, variants)
        );

        if(variants.isEmpty()){
            return createNoOptionContext(products);
        }

        String optionComment = getOptionComment(products);

        return new BuyMaOptionContext(options, variants, optionComment);
    }

    private String getOptionComment(List<ExternalSyncProduct> products){
        StringBuilder sb = new StringBuilder();
        products.forEach(p ->{
            sb.append(p.option()).append("\n");
        });

        sb.append("For more information, please contact us");
        return sb.toString();
    }


    private void populateOptionsWithNormalizedSizes(List<BuyMaOption> options, List<ExternalSyncCategoryOption> categoryOptions, ExternalSyncOptionResult optionsResult) {
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
            List<ExternalSyncCategoryOption> categoryOptions,
            List<ExternalSyncProduct> products,
            List<BuyMaVariant> variants
    ) {

        Map<Long, Integer> masterIdToStock = new HashMap<>();
        Map<Long, String> masterIdToOptionValue = new HashMap<>();

        for (ExternalSyncProduct product : products) {
            for (ExternalSyncOption productOption : product.options()) {
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

    private int calculateTotalQuantity(List<ExternalSyncProduct> products) {
        return products.stream()
                .map(ExternalSyncProduct::quantity)
                .reduce(0, Integer::sum);
    }

    private long findMatchingOptionId(String size, List<ExternalSyncCategoryOption> categoryOptions) {
        return categoryOptions.stream()
                .filter(option -> matchesRange(size, option.optionValue()))
                .map(ExternalSyncCategoryOption::optionId)
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
            Pattern pattern = Pattern.compile("([0-9.]+)(cm|mm)?");
            Matcher matcher = pattern.matcher(value);

            if (matcher.find()) {
                String numeric = matcher.group(1);
                String unit = matcher.group(2);

                double size = Double.parseDouble(numeric);


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
