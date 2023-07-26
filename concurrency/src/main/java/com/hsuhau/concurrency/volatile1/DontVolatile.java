package com.hsuhau.concurrency.volatile1;

import java.util.concurrent.atomic.AtomicInteger;

public class DontVolatile implements Runnable {

    volatile int a;

    AtomicInteger realA = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {

        Runnable r =  new DontVolatile();

        Thread thread1 = new Thread(r);

        Thread thread2 = new Thread(r);

        thread1.start();

        thread2.start();

        thread1.join();

        thread2.join();

        System.out.println(((DontVolatile) r).a);

        System.out.println(((DontVolatile) r).realA.get());

    }

    @Override

    public void run() {

        for (int i = 0; i < 1000; i++) {

            a++;

            realA.incrementAndGet();

        }

    }

}
