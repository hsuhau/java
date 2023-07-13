package com.hsuhau.threadpool.example;

import lombok.extern.slf4j.Slf4j;

/**

 * 描述：     for循环新建10个线程

 */

@Slf4j
public class TenTask {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {

            Thread thread = new Thread(new Task());

            thread.start();

        }

    }

    static class Task implements Runnable {

        public void run() {

            log.info("Thread Name: " + Thread.currentThread().getName());

        }

    }

}
