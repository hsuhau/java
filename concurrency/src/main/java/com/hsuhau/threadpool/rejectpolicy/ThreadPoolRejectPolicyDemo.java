package com.hsuhau.threadpool.rejectpolicy;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 描述：
 * 用固定线程数的线程池执行10000个任务
 */

/**
 * 使用线程池的好处
 * 使用线程池比手动创建线程主要有三点好处。
 * <p>
 * 第一点，线程池可以解决线程生命周期的系统开销问题，同时还可以加快响应速度。因为线程池中的线程是可以复用的，我们只用少量的线程去执行大量的任务，这就大大减小了线程生命周期的开销。而且线程通常不是等接到任务后再临时创建，而是已经创建好时刻准备执行任务，这样就消除了线程创建所带来的延迟，提升了响应速度，增强了用户体验。
 * 第二点，线程池可以统筹内存和 CPU 的使用，避免资源使用不当。线程池会根据配置和任务数量灵活地控制线程数量，不够的时候就创建，太多的时候就回收，避免线程过多导致内存溢出，或线程太少导致 CPU 资源浪费，达到了一个完美的平衡。
 * 第三点，线程池可以统一管理资源。比如线程池可以统一管理任务队列和线程，可以统一开始或结束任务，比单个线程逐一处理任务要更方便、更易于管理，同时也有利于数据统计，比如我们可以很方便地统计出已经执行过的任务的数量。
 */

@Slf4j
public class ThreadPoolRejectPolicyDemo {

    public static void main(String[] args) {

        ThreadPoolExecutor service = new ThreadPoolExecutor(
                5,
                10,
                5,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 0; i < 1000; i++) {
            long nanoTime = System.nanoTime();

            log.info(nanoTime + " 主线程: " + String.valueOf(i));

//            log.info(nanoTime + " core pool size: " + service.getCorePoolSize());
//            log.info(nanoTime + " maximum pool size: " + service.getMaximumPoolSize());
            log.info(nanoTime + " pool size: " + service.getPoolSize());

            log.info(nanoTime + " task count: " + service.getTaskCount());
            log.info(nanoTime + " completed task count: " + service.getCompletedTaskCount());
            log.info(nanoTime + " active count: " + service.getActiveCount());

            log.info(nanoTime + " queue size: " + service.getQueue().size());
//            log.info(nanoTime + " queue: " + service.getQueue());

            service.execute(new Task());

        }

        log.info(Thread.currentThread().getName());

    }

    static class Task implements Runnable {

        public void run() {

            long nanoTime = System.nanoTime();
            log.info(nanoTime + " Start 子线程: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info(nanoTime + " End 子线程: " + Thread.currentThread().getName());
        }

    }

}
