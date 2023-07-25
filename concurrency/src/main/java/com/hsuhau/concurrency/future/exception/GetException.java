package com.hsuhau.future.exception;

import java.util.concurrent.*;

public class GetException {

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(20);

        Future<Integer> future = service.submit(new CallableTask());

        try {

            for (int i = 0; i < 5; i++) {

                System.out.println(i);

                Thread.sleep(500);

            }

            System.out.println(future.isDone());

            future.get();

        } catch (InterruptedException e) {

            e.printStackTrace();

        } catch (ExecutionException e) {

            e.printStackTrace();

        }

    }

    static class CallableTask implements Callable<Integer> {

        @Override

        public Integer call() throws Exception {

            throw new IllegalArgumentException("Callable抛出异常");

        }

    }

}