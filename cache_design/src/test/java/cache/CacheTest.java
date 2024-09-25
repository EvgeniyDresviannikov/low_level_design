package cache;

import cache.policy.LRUEvictionPolicy;
import cache.storage.HashMapStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CacheTest {

    Cache<Integer, Integer> cache;

    @BeforeEach
    public void setup() {
        cache = new Cache<>(new LRUEvictionPolicy<>(),
                new HashMapStorage<>(3));
    }

    @Test
    public void itShouldBeAbleToGetAndAddItemsInTheCache() {
        cache.put(1, 1);
        cache.put(2, 2);

        assertEquals(1, cache.get(1)); // Accessing 1 after 2 got inserted which makes 2 the least recently used till now.
        cache.put(3, 3);
        assertEquals(3, cache.get(3));

        // Now if i try to add any element, the eviction should happen
        // Also eviction should happen based on LeastRecentlyUsedItem
        // which is 2 in this case.
        cache.put(4, 4);

        assertNull(cache.get(2)); // This should throw exception "Tried to access non-existing key."
    }
}