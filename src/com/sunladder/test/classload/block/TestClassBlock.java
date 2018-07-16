package com.sunladder.test.classload.block;

public class TestClassBlock {

    static class Test1 {

        public int value = 1;

        {
            value = 2;
        }
    }

    static class Test2 {

        {
            value = 2;
        }

        public int value = 1;

    }

    static class Test3 {

        {
            value = 2;
        }

        public int value = 1;

        {
            value = 3;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Test1().value);
        System.out.println(new Test2().value);
        System.out.println(new Test3().value);
    }
}
