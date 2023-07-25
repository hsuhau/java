package com.hsuhau.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Unfair {

    public static void main(String args[]) {

        FairPrintQueue fairPrintQueue = new FairPrintQueue();

        Thread thread[] = new Thread[10];

        for (int i = 0; i < 10; i++) {

            thread[i] = new Thread(new FairJob(fairPrintQueue), "Thread " + i);

        }

        for (int i = 0; i < 10; i++) {

            thread[i].start();

            try {

                Thread.sleep(100);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }

    }

}

class UnfairJob implements Runnable {

    private FairPrintQueue fairPrintQueue;

    public UnfairJob(FairPrintQueue fairPrintQueue) {

        this.fairPrintQueue = fairPrintQueue;

    }

    @Override

    public void run() {

        System.out.printf("%s: Going to print a job\n", Thread.currentThread().getName());

        fairPrintQueue.printJob(new Object());

        System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());

    }

}

class UnfairPrintQueue {

    private final Lock queueLock = new ReentrantLock(false);

    public void printJob(Object document) {

        queueLock.lock();

        try {

            Long duration = (long) (Math.random() * 10000);

            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n",

                    Thread.currentThread().getName(), (duration / 1000));

            Thread.sleep(duration);

        } catch (InterruptedException e) {

            e.printStackTrace();

        } finally {

            queueLock.unlock();

        }

        queueLock.lock();

        try {

            Long duration = (long) (Math.random() * 10000);

            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n",

                    Thread.currentThread().getName(), (duration / 1000));

            Thread.sleep(duration);

        } catch (InterruptedException e) {

            e.printStackTrace();

        } finally {

            queueLock.unlock();

        }

    }

}