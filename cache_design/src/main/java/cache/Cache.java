package cache;


import cache.exception.KeyNotFound;
import cache.exception.StorageFullException;
import cache.policy.LRUEvictionPolicy;
import cache.storage.Storage;

public class Cache<K, V> {

    private final LRUEvictionPolicy<K> policy;
    private final Storage<K, V> storage;

    public Cache(LRUEvictionPolicy<K> policy, Storage<K, V> storage) {
        this.policy = policy;
        this.storage = storage;
    }

    public V get(K key) {
        try {
            V value = this.storage.get(key);
            this.policy.keyAccessed(key);
            return value;
        } catch (KeyNotFound keyNotFoundException) {
            System.out.println("Tried to access non-existing key.");
            return null;
        }
    }

    public void put(K key, V value) {
        try {
            this.storage.add(key, value);
            this.policy.keyAccessed(key);
        } catch (StorageFullException exception) {
            System.out.println("Got storage full. Will try to evict.");
            K keyToRemove = policy.evict();
            if (keyToRemove == null) {
                throw new RuntimeException("Unexpected State. Storage full and no key to evict.");
            }
            this.storage.remove(keyToRemove);
            System.out.println("Creating space by evicting item..." + keyToRemove);
            put(key, value);
        }
    }
}
