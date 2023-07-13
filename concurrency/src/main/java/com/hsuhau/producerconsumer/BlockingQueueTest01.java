package com.hsuhau.producerconsumer;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class BlockingQueueTest01 {
    public static void main(String[] args) {

        BlockingQueue<Object> queue = new ArrayBlockingQueue<>(10);

        Runnable producer = () -> {

            while (true) {

                try {
                    queue.put(queue.size());
                    log.info("producer queue = " + queue);
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
                    log.info("consumer queue = " + queue);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

        };

        new Thread(consumer).start();

//        new Thread(consumer).start();

    }
}
