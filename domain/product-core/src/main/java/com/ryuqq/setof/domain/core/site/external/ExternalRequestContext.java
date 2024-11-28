package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaProductContext;

public class ExternalRequestContext {
    private long productGroupId;
    private boolean success;
    private BuyMaProductContext buyMaProductContext;

    public ExternalRequestContext(long productGroupId, boolean success, BuyMaProductContext buyMaProductContext) {
        this.productGroupId = productGroupId;
        this.success = success;
        this.buyMaProductContext = buyMaProductContext;
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    public boolean isSuccess() {
        return success;
    }

    public BuyMaProductContext getBuyMaProductContext() {
        return buyMaProductContext;
    }
}
