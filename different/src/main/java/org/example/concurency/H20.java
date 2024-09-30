package org.example.concurency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

class H2O {

    private CyclicBarrier barrier;
    private Semaphore hSemaphore;
    private Semaphore oSemaphore;

    public H2O() {
        this.barrier = new CyclicBarrier(3);
        this.hSemaphore = new Semaphore(2);
        this.oSemaphore = new Semaphore(1);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hSemaphore.acquire();
        awaitCyclicBarrier();
        releaseHydrogen.run();
        hSemaphore.release();

    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oSemaphore.acquire();
        awaitCyclicBarrier();
        releaseOxygen.run();
        oSemaphore.release(1);
    }

    private void awaitCyclicBarrier() {
        try {
            this.barrier.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
