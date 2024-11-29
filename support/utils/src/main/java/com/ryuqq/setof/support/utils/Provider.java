package com.ryuqq.setof.support.utils;

public interface Provider<K, T> {
    T get(K key);

}
