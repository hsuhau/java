package com.hsuhau.concurrency.producerconsumer;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * 描述：     wait形式实现生产者消费者模式
 */

@Slf4j
public class WaitStyle {

    public static void main(String[] args) {

        MyBlockingQueue myBlockingQueue = new MyBlockingQueue(10);

        Producer producer = new Producer(myBlockingQueue);

        Consumer consumer = new Consumer(myBlockingQueue);

        new Thread(producer).start();

        new Thread(consumer).start();

    }

}

class Producer implements Runnable {

    private MyBlockingQueue storage;

    public Producer(MyBlockingQueue storage) {

        this.storage = storage;

    }

    @Override

    public void run() {

        while (true) {

            try {

                storage.put();

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }

    }

}

@Slf4j
class Consumer implements Runnable {

    private MyBlockingQueue storage;

    public Consumer(MyBlockingQueue storage) {

        this.storage = storage;

    }

    @Override

    public void run() {

        while (true) {

            try {

                storage.take();

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }

    }

}

@Slf4j
class MyBlockingQueue {

    private int maxSize;

    private LinkedList<Object> storage;

    public MyBlockingQueue(int size) {

        this.maxSize = size;

        storage = new LinkedList<>();

    }

    public synchronized void put() throws InterruptedException {

        while (storage.size() == maxSize) {
            log.info("put wait");
            wait();

        }

        storage.add(storage.size());
        log.info("put storage = " + storage);

        notifyAll();

    }

    public synchronized void take() throws InterruptedException {

        while (storage.size() == 0) {
            log.info("take wait");
            wait();

        }
        storage.remove();
        log.info("take storage = " + storage);

        notifyAll();

    }

}
