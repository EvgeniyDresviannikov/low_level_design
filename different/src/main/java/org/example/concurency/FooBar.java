package org.example.concurency;

import java.util.concurrent.Semaphore;

class FooBar {
    private int n;
    private boolean fooBarFlag;
    private Semaphore fooSemaphore;
    private Semaphore barSemaphore;

    public FooBar(int n) {
        this.n = n;
        this.fooSemaphore = new Semaphore(1);
        this.barSemaphore = new Semaphore(0);
        fooBarFlag = true;
    }

    public void foo(Runnable printFoo) throws InterruptedException {


        for (int i = 0; i < n; i++) {
            fooSemaphore.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();

            barSemaphore.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {


        for (int i = 0; i < n; i++) {
            barSemaphore.acquire();

            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();

            fooSemaphore.release();
        }
    }
}
