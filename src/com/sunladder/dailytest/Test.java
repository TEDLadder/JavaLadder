package com.sunladder.dailytest;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Test {


    static class A {

        int i;
        static int a = 1;

        static {
            a = 2;
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

    private static List<String> mlist = new ArrayList<>();

    public static void main(String[] args) {
        Class<? extends Test> aClass = Test.class;
        try {
            Field field = aClass.getDeclaredField("mlist");
            Class<?> type = field.getType();
            Class<? extends List> aClass1 = type.asSubclass(List.class);

            Type genericType = field.getGenericType();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
