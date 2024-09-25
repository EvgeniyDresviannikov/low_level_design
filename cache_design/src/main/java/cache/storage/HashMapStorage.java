package cache.storage;

import cache.exception.KeyNotFound;
import cache.exception.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class HashMapStorage<K, V> implements Storage<K, V>{
    private final Map<K, V> storage;
    private final Integer capacity;

    public HashMapStorage(Integer capacity) {
        storage = new HashMap<>();
        this.capacity = capacity;
    }

    @Override
    public V get(K key) {
        if (!storage.containsKey(key)) throw new KeyNotFound(key + "doesn't exist in cache.");
        return storage.get(key);
    }

    @Override
    public void add(K key, V value) {
        if (isStorageFull()) throw new StorageFullException("Capacity Full.....");
        storage.put(key, value);
    }

    @Override
    public void remove(K key) {
        if (!storage.containsKey(key)) throw new KeyNotFound(key + "doesn't exist in cache.");
        storage.remove(key);
    }

    @Override
    public int size() {
        return storage.size();
    }

    private boolean isStorageFull() {
        return storage.size() == capacity;
    }


}
