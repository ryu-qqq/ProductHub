package com.ryuqq.setof.storage.db.index;

@FunctionalInterface
public interface ElasticsearchAction<T> {
    T perform() throws Exception;
}