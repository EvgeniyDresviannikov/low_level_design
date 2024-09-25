package cache.storage;

public interface Storage<K, V> {

    V get(K key);

    void add(K key, V value);

    void remove(K key);

    int size();
}
