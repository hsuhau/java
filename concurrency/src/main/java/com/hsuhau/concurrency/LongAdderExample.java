package com.hsuhau.concurrency.adder;

import java.util.concurrent.atomic.LongAdder;

public class LongAdderExample {
    public static void main(String[] args) {
        LongAdder adder = new LongAdder();

        // 多个线程对计数器进行增加操作
        Runnable incrementTask = () -> {
            for (int i = 0; i < 1000; i++) {
                adder.increment();
            }
        };

        Thread thread1 = new Thread(incrementTask);
        Thread thread2 = new Thread(incrementTask);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 获取最终的计数值
        long total = adder.sum();
        System.out.println("Total count: " + total);
    }
}
