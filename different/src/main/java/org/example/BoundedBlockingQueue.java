package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BoundedBlockingQueue {

    public static void main(String[] args) {
        BoundedBlockingQueue boundedBlockingQueue = new BoundedBlockingQueue(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 50; i++) {
            if (i%2 == 0) {
                int el = i;
                executorService.submit(() -> boundedBlockingQueue.enqueue(el));
            } else {
                executorService.submit(boundedBlockingQueue::dequeue);
            }
        }

        executorService.shutdown();
    }

    private int capacity;
    private Queue<Integer> queue;
    private Object enqueueLock;
    private Object dequeueLock;

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
        this.enqueueLock = new Object();
        this.dequeueLock = new Object();
    }

    public void enqueue(int element) {
        synchronized (enqueueLock) {
            System.out.println("going to add element " + element);
            while (queue.size() == capacity) {
                try {
                    enqueueLock.wait();
                } catch (InterruptedException e) {
                    System.out.println("thread was interrupted");
                }
            }

            queue.add(element);
            System.out.println("element " + element + " added");
            enqueueLock.notifyAll();
        }
    }

    public int dequeue() {
        synchronized (dequeueLock) {
            while (queue.size() == 0) {
                try {
                    dequeueLock.wait();
                } catch (InterruptedException e) {
                    System.out.println("thread was interrupted");;
                }
            }
            int el = queue.poll();
            System.out.println("element " + el + " removed");
            dequeueLock.notifyAll();
            return el;
        }
    }

    public int size() {
        return queue.size();
    }

}
