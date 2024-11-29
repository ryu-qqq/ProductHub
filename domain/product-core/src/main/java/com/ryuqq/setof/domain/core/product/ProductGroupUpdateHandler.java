package com.ryuqq.setof.domain.core.product;

public interface ProductGroupUpdateHandler<T> {
    void handle(long productGroupId, T command);
    Class<T> getType();
}
