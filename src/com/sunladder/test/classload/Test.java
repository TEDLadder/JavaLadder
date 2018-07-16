package com.sunladder.test.classload;

public class Test {


    static class A {

        static {
            a = 2;
            System.out.println("A static block");
        }

        public static int a = 1;

        static {
            a = 3;
            System.out.println("A static block");
        }

        int i;

        {
            System.out.println("A block");
        }

        public A(int a) {
            print();
        }

        void print() {
            System.out.println("A");
        }

        void print1() {
            System.out.println("A1");
            print();
        }
    }

    static class B extends A {

        static {
            System.out.println("B static block");
        }

        {
            System.out.println("B block");
        }

        public B(int a) {
            super(a);
        }

        @Override
        void print() {
            System.out.println("B");
        }

        @Override
        void print1() {
            super.print1();
            System.out.println("B1");
        }
    }

    public static void main(String[] args) {
        System.out.println(new A(1).a);
    }
}
