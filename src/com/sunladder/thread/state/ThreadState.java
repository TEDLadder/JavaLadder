package com.sunladder.thread.state;

import java.util.ArrayList;
import java.util.List;

/**
 *  线程状态
 *
 *  1.NEW
 *      a new instance
 *
 *  2.RUNNABLE
 *      in runnable thread pool,be waiting for processor
 *
 *  3.BLOCKED
 *      in lock pool
 *
 *  4.WAITING
 *      wait() join() ?LockSupport#park()
 *
 *  5.TIMED_WAITING
 *      wait(time) join(time) sleep(time) LockSupport#parkNanos LockSupport#parkUntil
 *
 *  6.TERMINATED
 *      不可重新start IllegalThreadStateException  if the thread was already
 *
 */

public class ThreadState {

    public static void main(String[] args) {
        CheckThread checkThread = new CheckThread();
        checkThread.start();

        final Thread th2 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        final Thread th1 = new Thread() {
            @Override
            public void run() {
                printState(Thread.currentThread());

                try {
                    Thread.currentThread().join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                }
            }
        };

        printState(th1);
        th1.start();
        checkThread.addTarget(th1);
    }

    static void printState(Thread th1) {
        System.out.println(th1.getState().toString());
    }

    static class CheckThread extends Thread {

        private final List<Thread> list = new ArrayList<>();

        void addTarget(Thread thread) {
            list.add(thread);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
                for (Thread thread : list) {
                    State state = thread.getState();
                    System.out.println(state.toString());
                    if (state == State.TERMINATED) {
                        thread.start();
                    }
                }
            }
        }
    }
}
