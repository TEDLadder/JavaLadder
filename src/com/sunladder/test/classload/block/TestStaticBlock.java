package com.sunladder.test.classload.block;

public class TestStaticBlock {

    static class Test1 {

        public static int value = 1;

        static {
            value = 2;
        }
    }

    static class Test2 {

        static {
            value = 2;
        }

        public static int value = 1;

    }

    static class Test3 {

        static {
            value = 2;
        }

        public static int value = 1;

        static {
            value = 3;
        }
    }

    public static void main(String[] args) {
        System.out.println(Test1.value);
        System.out.println(Test2.value);
        System.out.println(Test3.value);
    }
}
