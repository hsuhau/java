package com.hsuhau.producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest01 {
    public static void main(String[] args) {

        BlockingQueue<Object> queue = new ArrayBlockingQueue<>(10);

        Runnable producer = () -> {

            while (true) {

                try {
                    queue.put(queue.size());
                    System.out.println("producer queue = " + queue);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

        };

        new Thread(producer).start();

//        new Thread(producer).start();

        Runnable consumer = () -> {

            while (true) {

                try {
                    queue.take();
                    System.out.println("consumer queue = " + queue);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

        };

        new Thread(consumer).start();

//        new Thread(consumer).start();

    }
}
