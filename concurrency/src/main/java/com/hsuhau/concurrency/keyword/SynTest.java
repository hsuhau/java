package com.hsuhau.concurrency.keyword;

public class SynTest {

    public void synBlock() {

        synchronized (this) {

            System.out.println("lagou");

        }

    }

    public synchronized void synMethod() {

    }

}