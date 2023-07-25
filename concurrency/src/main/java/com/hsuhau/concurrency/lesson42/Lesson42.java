package com.hsuhau.concurrency.lesson42;

public class Lesson42 implements Runnable {

    static int value = 0;

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = new Lesson42();

        Thread thread1 = new Thread(runnable);

        Thread thread2 = new Thread(runnable);

        thread1.start();

        thread2.start();

        thread1.join();

        thread2.join();

        System.out.println(value);

    }

    @Override

    public void run() {

        for (int i = 0; i < 10000; i++) {

            value++;

        }

    }

}
