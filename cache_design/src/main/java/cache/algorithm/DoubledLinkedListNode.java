package cache.algorithm;

public class DoubledLinkedListNode<E> {

    private DoubledLinkedListNode<E> prev;
    private DoubledLinkedListNode<E> next;
    private E item;

    public DoubledLinkedListNode<E> getPrev() {
        return prev;
    }

    public DoubledLinkedListNode<E> getNext() {
        return next;
    }

    public E getItem() {
        return item;
    }

    public void setPrev(DoubledLinkedListNode<E> prev) {
        this.prev = prev;
    }

    public void setNext(DoubledLinkedListNode<E> next) {
        this.next = next;
    }

    public void setItem(E item) {
        this.item = item;
    }
}
