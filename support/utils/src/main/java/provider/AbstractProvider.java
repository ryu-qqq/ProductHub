package provider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractProvider<K, T> implements Provider<K, T> {
    protected final Map<K, T> map = new ConcurrentHashMap<>();

    @Override
    public T get(K key) {
        T value = map.get(key);
        if (value == null) {
            throw new IllegalArgumentException("No strategy found for key: " + key.toString());
        }
        return value;
    }

}

