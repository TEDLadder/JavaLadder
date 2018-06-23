package com.sunladder.algorithm;

import java.util.Arrays;

public class Util {

    public static int[] getArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        System.out.println("***************origin arr***************");
        System.out.println(Arrays.toString(arr));
        System.out.println("******************start*****************");
        return arr;
    }

    public static void printArr(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    private static final ThreadLocal<Long> sThreadLocal = new ThreadLocal<>();

    public static void start() {
        sThreadLocal.set(System.currentTimeMillis());
    }

    public static void end() {
        Long start = sThreadLocal.get();
        if (start != null) {
            System.out.println("time cost:" + (System.currentTimeMillis() - start));
        }
    }
}
