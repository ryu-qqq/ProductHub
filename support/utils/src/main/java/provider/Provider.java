package provider;

public interface Provider<K, T> {
    T get(K key);

}
