package cache.policy;

import cache.algorithm.DoubledLinkedList;
import cache.algorithm.DoubledLinkedListNode;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {

    private final DoubledLinkedList<K> dll;
    private final Map<K, DoubledLinkedListNode<K>> map;

    public LRUEvictionPolicy() {
        this.dll = new DoubledLinkedList<>();
        this.map = new HashMap<>();
    }

    @Override
    public void keyAccessed(K key) {
        if (map.containsKey(key)) {
            dll.detachNode(map.get(key));
            dll.addNodeAtLast(map.get(key));
        } else {
            DoubledLinkedListNode<K> newNode = dll.addElementAtLast(key);
            map.put(key, newNode);
        }
    }

    @Override
    public K evict() {
        DoubledLinkedListNode<K> first = dll.getFirstNode();
        if(first == null) {
            return null;
        }
        dll.detachNode(first);
        return first.getItem();
    }
}
