package com.zhaowq.jvm.chapter08;

/**
 * @author zhaowq
 * @Date 2018/9/19
 */
public class NotInitialization {
    public static void main(String[] args) {
       // System.out.println(SubClass.value);
       // SuperClass[] sca = new SuperClass[10];
        System.out.println(ConstClass.HELLOWORLD);
    }
}
