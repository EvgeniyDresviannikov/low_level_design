package org.example.concurency;

import java.util.function.IntConsumer;

class FizzBuzz {

    public static void main(String[] args) throws InterruptedException {
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        Runnable printFizz = () -> System.out.println("fizz");
        Runnable printBuzz = () -> System.out.println("buzz");
        Runnable printFizzBuzz = () -> System.out.println("fizzBuzz");
        IntConsumer printNumber = System.out::println;

        Thread thread1 = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(printFizzBuzz);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                fizzBuzz.fizz(printFizz);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                fizzBuzz.buzz(printBuzz);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread4 = new Thread(() -> {
            try {
                fizzBuzz.number(printNumber);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
    }

    private int n;
    private volatile int counter;


    public FizzBuzz(int n) {
        this.n = n;
        this.counter = 1;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (counter <= this.n) {
            synchronized (this) {
                while (!(counter % 3 == 0 && counter % 5 != 0)) {
                    wait();
                    if (counter > this.n) return;
                }
                printFizz.run();
                counter++;
                notifyAll();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (counter <= this.n) {
            synchronized (this) {
                while (!(counter % 5 == 0 && counter % 3 != 0)) {
                    wait();
                    if (counter > this.n) return;
                }
                printBuzz.run();
                counter++;
                notifyAll();
            }

        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (counter <= this.n) {
            synchronized (this) {
                while (!(counter % 3 == 0 && counter % 5 == 0)) {
                    wait();
                    if (counter > this.n) return;
                }
                printFizzBuzz.run();
                counter++;
                notifyAll();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (counter <= this.n) {
            synchronized (this) {
                while (!(counter % 3 != 0 && counter % 5 != 0)) {
                    wait();
                    if (counter > this.n) return;
                }
                printNumber.accept(counter);
                counter++;
                notifyAll();
            }
        }
    }
}