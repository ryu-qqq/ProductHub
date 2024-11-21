package com.ryuqq.setof.domain.core.product;

public class UpdateDomain<T> {

    private final T domain;
    private final boolean isRealTime;

    public UpdateDomain(T domain, boolean isRealTime) {
        this.domain = domain;
        this.isRealTime = isRealTime;
    }

    public T getDomain() {
        return domain;
    }

    public boolean isRealTime() {
        return isRealTime;
    }

}
