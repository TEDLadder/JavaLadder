package com.sunladder.thread.sync;

/**
 *  死锁的四个必要条件
 *  1.互斥条件
 *      一个资源每次只能被一个进程使用，即在一段时间内某 资源仅为一个进程所占有。此时若有其他进程请求该资源，则请求进程只能等待。
 *
 *  2.请求与保持条件
 *      进程已经保持了至少一个资源，但又提出了新的资源请求，而该资源 已被其他进程占有，此时请求进程被阻塞，但对自己已获得的资源保持不放。
 *
 *  3.不可剥夺条件
 *      进程所获得的资源在未使用完毕之前，不能被其他进程强行夺走，即只能 由获得该资源的进程自己来释放（只能是主动释放)。
 *
 *  4.循环等待条件:
 *      若干进程间形成首尾相接循环等待资源的关系
 *
 *  避免死锁的方法
 *  1.死锁预防  破坏一或多个条件
 *
 *  2.死锁避免  银行家算法
 *
 *  3.死锁检测
 *
 *  4.死锁解除 剥夺资源 撤销进程
 */

public class DeadLock {

    static class Source {

        synchronized void getSource() {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        final Source s1 = new Source();
        final Source s2 = new Source();

        new Thread("1") {
            @Override
            public void run() {
                for (; ; ) {
                    synchronized (s1) {
                        s1.getSource();
                        synchronized (s2) {
                            s2.getSource();
                        }
                    }
                }
            }
        }.start();

        new Thread("2") {
            @Override
            public void run() {
                for (; ; ) {
                    synchronized (s2) {
                        s2.getSource();
                        synchronized (s1) {
                            s1.getSource();
                        }
                    }
                }
            }
        }.start();
    }
}
