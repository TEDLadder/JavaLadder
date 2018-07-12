package com.sunladder.test;


/**
 * 关于运行中的线程与gc问题
 * <p>
 * 说明：
 * 主线程持续gc  子线程运行5s 10s时引用置null
 * <p>
 * 测试项目：
 * 1.未引用，running中是否会被gc
 * 2.有引用，running中与terminated后时候被gc
 * <p>
 * gc日志：
 * -XX:+PrintGC 输出GC日志
 * -XX:+PrintGCDetails 输出GC的详细日志
 * -XX:+PrintGCTimeStamps 输出GC的时间戳（以基准时间的形式）
 * -XX:+PrintGCDateStamps 输出GC的时间戳（以日期的形式，如 2013-05-04T21:53:59.234+0800）
 * -XX:+PrintHeapAtGC 在进行GC的前后打印出堆的信息
 * -Xloggc:../logs/gc.log 日志文件的输出路径
 *
 * <p>
 * The JVM has a reference to all running threads.
 * No thread (or the things it refers to) will be garbage-collected while it is still running.
 * <p>
 * A running thread is considered a so called garbage collection root and is one of those things keeping stuff from being garbage collected.
 * When the garbage collector determines whether your object is 'reachable' or not, it is always doing so using the set of garbage collector roots as reference points.
 */
public class TestGC0712 {

    public static void main(String[] args) {
        Bean bean = new Bean();

        for (int i = 0; ; i++) {
            try {
                Thread.sleep(1000);
                System.gc();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            System.out.println(Thread.currentThread().getThreadGroup().activeCount());

            if (i > 10) {
                bean = null;
            }
        }
    }

    static class Bean {

        private byte[] malloc = new byte[10 * 1024];

        public Bean() {
            new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
    }
}
