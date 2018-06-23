package com.sunladder.algorithm.sort;

import com.sunladder.algorithm.Util;

import java.util.Stack;

/**
 * 快速排序
 *
 *  1.递归实现 挖坑/前后指针
 *
 *  2.非递归实现
 *
 *  3.优化点
 *      1>中轴:三数取中法,避免取到差值
 *      2>分区:特殊方案处理单独分区
 *      3>并行处理
 *
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = Util.getArray(20);

        quickSort(arr);
    }


    private static void quickSort(int[] arr) {
        Util.start();
        quickSort1(arr, 0, arr.length - 1);
        Util.end();
        Util.printArr(arr);

        Util.start();
        quickSort2(arr, 0, arr.length - 1);
        Util.end();
        Util.printArr(arr);

        Util.start();
        quickSortNotR(arr);
        Util.end();
        Util.printArr(arr);
    }


    /*1 递归 挖坑法*/
    private static void quickSort1(int[] arr, int l, int r) {
        if (l < r) {
            int middle = partSort(arr, l, r);
            quickSort1(arr, l, middle - 1);
            quickSort1(arr, middle + 1, r);
        }
    }


    private static int partSort(int[] arr, int l, int r) {
        int tempValue = arr[l];
        while (l < r) {
            while (l < r && arr[r] >= tempValue) {
                r--;
            }
            arr[l] = arr[r];

            while (l < r && arr[l] <= tempValue) {
                l++;
            }
            arr[r] = arr[l];
        }
        arr[l] = tempValue;
        return l;
    }

    /*2 前后指针法 适用于链表*/
    private static void quickSort2(int[] arr, int l, int r) {
        if (l < r) {
            int middle = pointerPart(arr, l, r);
            quickSort2(arr, l, middle - 1);
            quickSort2(arr, middle + 1, r);
        }
    }

    private static int pointerPart(int[] arr, int l, int r) {
        int key = arr[l];
        int cur = l;
        int pre = cur;
        while (cur <= r) {
            if (arr[cur] < key) {
                int temp = arr[cur];
                for (int i = cur; i > pre; i--) {
                    arr[i] = arr[i - 1];
                }
                arr[pre] = temp;
                pre++;
            }
            cur++;
        }
        return pre;
    }

    /*3 非递归实现 to avoid StackOverflowError*/
    private static void quickSortNotR(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(arr.length - 1);

        int left;
        int right;
        while (!stack.empty()) {
            right = stack.pop();
            left = stack.pop();
            int middle = partSort(arr, left, right);

            if (middle < left) {
                stack.push(left);
                stack.push(middle - 1);

                stack.push(middle + 1);
                stack.push(right);
            }
        }
    }
}
