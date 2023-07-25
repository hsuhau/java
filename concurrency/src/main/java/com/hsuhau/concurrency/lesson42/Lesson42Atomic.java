package com.hsuhau.concurrency.lesson42;

import java.util.concurrent.atomic.AtomicInteger;

public class     Lesson42Atomic implements Runnable {

    static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = new Lesson42Atomic();

        Thread thread1 = new Thread(runnable);

        Thread thread2 = new Thread(runnable);

        thread1.start();

        thread2.start();

        thread1.join();

        thread2.join();

        System.out.println(atomicInteger.get());

    }

    @Override

    public void run() {

        for (int i = 0; i < 10000; i++) {

            atomicInteger.incrementAndGet();

        }

    }

}
