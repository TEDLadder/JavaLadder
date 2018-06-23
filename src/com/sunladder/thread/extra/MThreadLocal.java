package com.sunladder.thread.extra;

/**
 * Threadlocal 如何维护各线程的变量副本
 *
 * 存过程{@link ThreadLocal#set(Object)}
 * 1. 会先取 {@link Thread#threadLocals} : 每个Thread可以有多个ThreadLocal通过ThreadLocalMap维护
 * 2.如果没有会进行创建{@link ThreadLocal.createMap()} ThreadLocalMap -> Thread ,add first element (key:ThreadLocal,value:<T>value)
 * 3.如果有add element (key:ThreadLocal,value:<T>value)
 *
 * <p>
 * 取过程{@link ThreadLocal#get()}
 * 1.get {@link Thread#threadLocals} -> ThreadLocalMap
 * 2.如果有get element (key:ThreadLocal)
 * 3.如果没有{@link ThreadLocal#setInitialValue()} -> {@link Thread#threadLocals} create if null -> add InitialValue and return it
 *
 * 如何保证线程隔离
 * Threadlocal instance - 1:n - Thread
 * Threadlocal在不同线程中取值时,取的是当前Thread下ThreadLocalMap中以此instance为key对应的value,所以是独立的
 */

public class MThreadLocal {

    public static String sStr = "1";
    public static final ThreadLocal<String> sThreadLocal = new ThreadLocal();

    public static void main(String[] args) {
        testThreadLocal();
    }

    private static void testThreadLocal() {
        new Thread() {
            @Override
            public void run() {
                sThreadLocal.set("1");
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(sThreadLocal.get());
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                sThreadLocal.set("2");
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sThreadLocal.set("" + System.currentTimeMillis());
                }
            }
        }.start();
        sThreadLocal.set(Thread.currentThread().getName());
    }

}
