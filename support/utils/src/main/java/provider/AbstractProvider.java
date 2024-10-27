package provider;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractProvider<K, T> implements Provider<K, T> {
    protected final Map<K, T> map = new HashMap<>();

    @Override
    public T get(K key) {
        T value = map.get(key);
        if (value == null) {
            throw new IllegalArgumentException("No strategy found for key: " + key.toString());
        }
        return value;
    }



}

