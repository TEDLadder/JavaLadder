package com.sunladder.test.classload.ploy;

/*
 * 多态的关键
 * invokespecial与invokevirtual的区别
 *
 */
public class TestPoly {

    static class Parent {

        public Parent() {
            // 1: invokespecial #1                  // Method java/lang/Object."<init>":()V

            // 5: invokevirtual #2                  // Method print:()V
            print();
        }

        void print() {
            System.out.println("Parent");
        }
    }


    static class Child extends Parent {

        public Child() {
            // 1: invokespecial #1
            // Method com/sunladder/test/classload/ploy/TestPoly$Parent."<init>":()V
        }

        @Override
        void print() {
            System.out.println("Child");
        }
    }

    public static void main(String[] args) {
        new Child();
    }
}
