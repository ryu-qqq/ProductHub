package com.ryuqq.setof.support.external.core.buyma.domain;

import com.ryuqq.setof.support.external.core.ExternalMallNameContext;
import com.ryuqq.setof.support.external.core.ExternalMallProductDetailContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BuyMaProductDetailContext implements ExternalMallProductDetailContext {

    private static final List<BuyMaShippingMethod> DEFAULT_SHIPPING_METHODS = List.of(
            new BuyMaShippingMethod(984481),
            new BuyMaShippingMethod(984491)
    );

    private static String DEFAULT_REFERENCE_NUMBER(String styleCode, long productGroupId) {
        StringBuilder sb = new StringBuilder();
        sb.append(productGroupId);

        if (styleCode != null && !styleCode.isEmpty()) {
            sb.append("_");
            sb.append(styleCode);
        }
        return sb.toString();
    }

    private final String id;
    private final String referenceNumber;
    private final String control;
    private final String availableUntil;
    private final int buyingAreaId;
    private final int shippingAreaId;
    private final List<BuyMaShippingMethod> buyMaShippingMethods;
    private final String duty;
    private final ExternalMallNameContext externalMallNameContext;

    public BuyMaProductDetailContext(String id, String styleCode, long productGroupId, ExternalMallNameContext externalMallNameContext) {
        this.id = id;
        this.referenceNumber = DEFAULT_REFERENCE_NUMBER(styleCode, productGroupId);
        this.control = "publish";
        this.availableUntil = LocalDateTime.now().plusDays(90).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.buyingAreaId = 2002003001;
        this.shippingAreaId = 2002003001;
        this.buyMaShippingMethods = DEFAULT_SHIPPING_METHODS;
        this.duty = "included";
        this.externalMallNameContext = externalMallNameContext;
    }

    @Override
    public ExternalMallNameContext getExternalMallNameContext() {
        return externalMallNameContext;
    }

    @Override
    public Object getCustomAttributes() {
        return new BuyMaCustomAttributes(id, referenceNumber, control, availableUntil, buyingAreaId, shippingAreaId, buyMaShippingMethods, duty);
    }

    public record BuyMaCustomAttributes(String id, String referenceNumber, String control, String availableUntil,
                                        int buyingAreaId, int shippingAreaId,
                                        List<BuyMaShippingMethod> buyMaShippingMethods, String duty) {
    }

}
