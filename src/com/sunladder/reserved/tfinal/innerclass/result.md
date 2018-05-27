[TOC]

# 1.编译得到4个class文件
main所在类 - class FinalForInnerClass

Outer类 - class FinalForInnerClass$Outer

outerRun方法中的匿名类1,访问变量 - class FinalForInnerClass$Outer$1

outerRun方法中的匿名类2,不访问任何变量 - class FinalForInnerClass$Outer$2

# 2.分析反编译结果 - 匿名类
```
class FinalForInnerClass$Outer$1 extends Thread {

	// $FF: synthetic field
    final Outer this$0;

    FinalForInnerClass$Outer$1(Outer var1, Object var2, int var3, String var4, Object var5) {
        this.this$0 = var1;
        this.val$methodObject1 = var2;
        this.val$methodNum2 = var3;
        this.val$methodString2 = var4;
        this.val$methodObject2 = var5;
    }

    public void run() {
        System.out.println("1 str1 " + Outer.outerObj1);
        System.out.println("2 str2 " + this.this$0.outerObj2);
        System.out.println(this.this$0.outerNum3 + " " + this.this$0.outerString3 + " " + this.this$0.outerObj3);
        System.out.println("0 methodString " + this.val$methodObject1);
        System.out.println(this.val$methodNum2 + " " + this.val$methodString2 + " " + this.val$methodObject2);
    }
}
```
```
class FinalForInnerClass$Outer$2 extends Thread {

    // $FF: synthetic field
    final Outer this$0;

    FinalForInnerClass$Outer$2(Outer var1) {
        this.this$0 = var1;
    }

    public void run() {
    }
}
```

1. 持有了外部类
```
1>构造方法中很明显看到有 Outer var1参数传入,并通过this.this$0 = var1持有
    FinalForInnerClass$Outer$1(Outer var1, Object var2, int var3, String var4, Object var5) {
        this.this$0 = var1;    
    }
2>即使不访问外部变量,仍持有了外部类
    FinalForInnerClass$Outer$2(Outer var1) {
            this.this$0 = var1;
    }
```

2. 编译优化的变量不会被持有
```
public static final int outerNum1 = 1;
public static final String outerString1 = "str1";
System.out.println(outerNum1 + " " + outerString1 + " " + outerObj1);
--> System.out.println("1 str1 " + Outer.outerObj1);
```

   
   
3. 对象类型被作为构造参数传入并持有
```
FinalForInnerClass$Outer$1(Outer var1, Object var2, int var3, String var4, Object var5) {}
```

   