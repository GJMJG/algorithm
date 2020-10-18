package test;

public class StringOverrideEquals {
    public static void main(String[] args) {
        String a1 = "aaa"; //分配在运行时常量池
        String a2 = "aa" + new String("a"); //分配在堆中
        String a3 = new String("aaa"); //在堆中新建一个对象

        System.out.println("a1.intern().equals(a1)" + a1.intern().equals(a1));
        System.out.println("a1.intern().equals(a2)" + a1.intern().equals(a2));
        System.out.println("a3.intern().equals(a1)" + a3.intern().equals(a1));

        System.out.println("a1.intern() == a1 " + (a1.intern() == a1));
        System.out.println("a2.intern() == a1 " + (a2.intern() == a1));
        System.out.println("a2 == a3" + (a2 == a3)); //false 两个对象，地址不同
        System.out.println("a3.intern() == a2.inter() " + (a3.intern() == a2.intern())); //true
        // intern函数获取常量池地址，两者指向相同的常量池变量

        System.out.println("hashCode() : " + (a1.hashCode() == a2.hashCode()));
        System.out.println("== : " + (a1 == a2));
    }
}
