package com.sunladder.algorithm.sort;

import com.sunladder.algorithm.Util;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = Util.getArray(10);
        sort(arr);
    }

    private static void sort(int[] arr) {
        Util.start();
        mergeSort1(arr, 0, arr.length - 1);
        Util.end();
        Util.printArr(arr);
    }

    /*1 递归实现*/
    private static void mergeSort1(int[] arr, int l, int r) {
        partSort(arr, l, r);
    }

    private static void partSort(int[] arr, int l, int r) {
        if (l < r) {
            int middle = (l + r) / 2;
            partSort(arr, l, middle);
            partSort(arr, middle + 1, r);

            mergePart(arr, l, r, middle);
        }
    }

    private static void mergePart(int[] arr, int l, int r, int middle) {
        int[] tempArr = new int[r - l + 1];
        int index = 0;
        int i = l;
        int j = middle + 1;
        while (index < tempArr.length) {
            if (i <= middle && j <= r) {
                if (arr[i] <= arr[j]) {
                    tempArr[index] = arr[i];
                    i++;
                } else if (arr[j] <= arr[i]) {
                    tempArr[index] = arr[j];
                    j++;
                }
            } else if (i <= middle) {
                tempArr[index] = arr[i];
                i++;
            } else if (j <= r) {
                tempArr[index] = arr[j];
                j++;
            }
            index++;
        }

        index = 0;
        while (index < tempArr.length) {
            arr[l + index] = tempArr[index];
            index++;
        }
    }

    /*1 递归实现*/
    private static void mergeSort2(int[] arr) {

    }
}
