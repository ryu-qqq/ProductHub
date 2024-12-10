package com.ryuqq.setof.support.external.core.shein.domain;

import com.ryuqq.setof.support.external.core.ExternalMallNameContext;

import java.util.List;


public class SheInProductDetailContext implements ExternalMallProductDetailContext {

    private static String DEFAULT_SUPPLIER_CODE(String styleCode, long productGroupId) {
        StringBuilder sb = new StringBuilder();
        sb.append(productGroupId);

        if (styleCode != null && !styleCode.isEmpty()) {
            sb.append("_");
            sb.append(styleCode);
        }
        return sb.toString();
    }

    private final int editType;
    private final long productTypeId;
    private final String sourceSystem;
    private final int suitFlag;
    private final String supplierCode;
    private final List<SheInLanguageName> multiLanguageNameList;
    private final List<SheInSite> siteList;
    private final ExternalMallNameContext externalMallNameContext;

    public SheInProductDetailContext(long siteCategoryExtraId, String styleCode, long productGroupId, ExternalMallNameContext externalMallNameContext) {
        this.editType = 0;
        this.productTypeId = siteCategoryExtraId;
        this.sourceSystem = "openapi";
        this.suitFlag = 0;
        this.supplierCode = DEFAULT_SUPPLIER_CODE(styleCode, productGroupId);
        this.multiLanguageNameList = List.of(new SheInLanguageName("en", externalMallNameContext.getName()));
        this.siteList = List.of(new SheInSite("shein", List.of("shein-us")));
        this.externalMallNameContext = externalMallNameContext;
    }

    @Override
    public ExternalMallNameContext getExternalMallNameContext() {
        return externalMallNameContext;
    }

    @Override
    public Object getCustomAttributes() {
        return new SheInCustomAttributes(editType, productTypeId, sourceSystem, suitFlag, supplierCode, multiLanguageNameList, siteList);
    }

    public record SheInCustomAttributes(int editType, long productTypeId, String sourceSystem, int suitFlag,
                                        String supplierCode, List<SheInLanguageName> multiLanguageNameList,
                                        List<SheInSite> siteList) {
    }

}

