package cache.algorithm;

import cache.exception.InvalidElementException;

import java.util.NoSuchElementException;

public class DoubledLinkedList<K> {
    private DoubledLinkedListNode<K> dummyHead;
    private DoubledLinkedListNode<K> dummyTail;


    public DoubledLinkedList() {
        dummyHead = new DoubledLinkedListNode<>();
        dummyTail = new DoubledLinkedListNode<>();

        dummyHead.setNext(dummyTail);
        dummyTail.setPrev(dummyHead);
    }

    public boolean isEmpty() {
        return dummyHead.getNext() == dummyTail;
    }

    public void detachNode(DoubledLinkedListNode<K> node) {

        if (node != null) {
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
        }
    }

    public DoubledLinkedListNode<K> addElementAtLast(K element) {
        if (element == null) {
            throw new InvalidElementException();
        }
        DoubledLinkedListNode<K> newNode = new DoubledLinkedListNode<>();
        newNode.setItem(element);
        addNodeAtLast(newNode);
        return newNode;
    }

    public void addNodeAtLast(DoubledLinkedListNode<K> node) {
        DoubledLinkedListNode tailPrev = dummyTail.getPrev();
        tailPrev.setNext(node);
        node.setNext(dummyTail);
        dummyTail.setPrev(node);
        node.setPrev(tailPrev);
    }

    public DoubledLinkedListNode getFirstNode() throws NoSuchElementException {
        DoubledLinkedListNode item = null;
        if (isEmpty()) {
            return null;
        }
        return dummyHead.getNext();
    }

    public DoubledLinkedListNode getLastNode() throws NoSuchElementException {
        DoubledLinkedListNode item = null;
        if (isEmpty()) {
            return null;
        }
        return dummyTail.getPrev();
    }
}
