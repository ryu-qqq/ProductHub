package com.ryuqq.setof.domain.core.product;

// Todo:: 현재는 시간이 없어서 실시간 처리 하지만 나중에 배치 처리할것

public interface ProductGroupUpdateHandler<T> {
    void handle(long productGroupId, T command);
    Class<T> getType();
}
