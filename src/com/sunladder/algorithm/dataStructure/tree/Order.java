package com.sunladder.algorithm.dataStructure.tree;

public class Order {
    public static void main(String[] args) {

        int value = -17;
        System.out.println(getBinStr(value));
    }

    private static String getBinStr(int value) {
        if (value == 0) {
            return "";
        }

        boolean minus = value < 0;
        value = Math.abs(value);
        StringBuilder stringBuilder = new StringBuilder();
        int divider = 2;
        int temp;
        while (value / divider > 0) {
            temp = value % divider / (divider >> 1);
            stringBuilder.insert(0, temp);
            divider *= 2;
        }
        stringBuilder.insert(0, '1');
        if (minus) {
            stringBuilder.insert(0, '-');
        }
        return stringBuilder.toString();
    }
}
