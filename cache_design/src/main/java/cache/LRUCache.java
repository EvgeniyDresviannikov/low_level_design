package cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private final int capacity;
    private final Map<Integer, Node> cache;
    private final DoublyLinkedList lru;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.lru = new DoublyLinkedList();
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;

        Node node = cache.get(key);
        lru.detach(node);
        lru.setToTail(node);

        return node.val;

    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node != null) {
            node.val = value;
            lru.detach(node);
        } else {
            node = new Node(key, value);
            if (cache.size() == capacity) {
                int keyToRemove = lru.removeNodeFromHead();
                cache.remove(keyToRemove);
            }
            cache.put(key, node);
        }
        lru.setToTail(node);
    }
}

class Node {
    int key;
    int val;
    Node prev;
    Node next;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

class DoublyLinkedList {
    Node head;
    Node tail;

    public DoublyLinkedList() {
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.setNext(tail);
        tail.setPrev(head);
    }

    public void detach(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        //detach
        node.setPrev(null);
        node.setNext(null);

        prev.setNext(next);
        next.setPrev(prev);
    }

    public void setToTail(Node node) {
        Node beforeTail = tail.prev;
        beforeTail.setNext(node);
        node.setPrev(beforeTail);
        node.setNext(tail);
        tail.setPrev(node);
    }

    public int removeNodeFromHead() {
        if (isEmpty()) return -1;

        Node afterHead = head.next;
        detach(afterHead);
        return afterHead.key;
    }

    public boolean isEmpty() {
        return head.next == tail;
    }
}
