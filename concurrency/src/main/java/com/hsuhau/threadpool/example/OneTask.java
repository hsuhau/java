package com.hsuhau.threadpool.example;

import lombok.extern.slf4j.Slf4j;

/**

 * 描述：     单个任务的时候，新建线程来执行

 */

@Slf4j
public class OneTask {

    public static void main(String[] args) {

        Thread thread0 = new Thread(new Task());

        thread0.start();

    }

    static class Task implements Runnable {

        public void run() {

            log.info("Thread Name: " + Thread.currentThread().getName());

        }

    }

}