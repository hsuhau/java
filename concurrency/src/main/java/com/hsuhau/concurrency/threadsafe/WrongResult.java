package com.hsuhau.concurrency.threadsafe;

import lombok.extern.slf4j.Slf4j;

/**
 * 运行结果错误
 * 首先，来看多线程同时操作一个变量导致的运行结果错误。
 */

@Slf4j
public class WrongResult {

    volatile static int i;

    public static void main(String[] args) throws InterruptedException {

        Runnable r = new Runnable() {

            @Override

            public void run() {

                for (int j = 0; j < 10000; j++) {

                    i++;
//                    log.info("i = " + i);
//                    log.info("j = " + j);
                }

            }

        };

        Thread thread1 = new Thread(r);

        thread1.start();

        Thread thread2 = new Thread(r);

        thread2.start();

        thread1.join();

        thread2.join();

        log.info(String.valueOf(i));

    }

}