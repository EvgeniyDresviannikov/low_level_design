package org.example.concurency;

import java.util.concurrent.Semaphore;

class PrintInOrder {

    private Semaphore s1;
    private Semaphore s2;

    public PrintInOrder() {
        s1 = new Semaphore(0);
        s2 = new Semaphore(0);
    }

    public  void first(Runnable printFirst) throws InterruptedException {

        printFirst.run();

        s1.release();

    }

    public void second(Runnable printSecond) throws InterruptedException {
        s1.acquire();

        printSecond.run();

        s2.release();


    }

    public void third(Runnable printThird) throws InterruptedException {

        s2.acquire();

        printThird.run();

    }
}

