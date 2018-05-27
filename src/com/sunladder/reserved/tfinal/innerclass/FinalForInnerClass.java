package com.sunladder.reserved.tfinal.innerclass;

public class FinalForInnerClass {

    public static void main(String[] args) {
        new Outer().outerRun();
    }

    static class Outer {

        public static final int outerNum1 = 1;
        public static final String outerString1 = "str1";
        public static final Object outerObj1 = new Object();

        public final int outerNum2 = 2;
        public final String outerString2 = "str2";
        public final Object outerObj2 = new Object();

        public int outerNum3 = 3;
        public String outerString3 = "str3";
        public Object outerObj3 = new Object();

        void outerRun() {
            final int methodNum1 = 0;
            final String methodString1 = "methodString";
            final Object methodObject1 = new Object();

            final int methodNum2 = -outerNum3;
            final String methodString2 = outerString3.concat("*");
            final Object methodObject2 = new Object();

            new Thread() {
                @Override
                public void run() {
                    System.out.println(outerNum1 + " " + outerString1 + " " + outerObj1);
                    System.out.println(outerNum2 + " " + outerString2 + " " + outerObj2);
                    System.out.println(outerNum3 + " " + outerString3 + " " + outerObj3);

                    System.out.println(methodNum1 + " " + methodString1 + " " + methodObject1);
                    System.out.println(methodNum2 + " " + methodString2 + " " + methodObject2);
                }
            }.start();

            new Thread() {
                @Override
                public void run() {
                }
            }.start();
        }
    }
}
