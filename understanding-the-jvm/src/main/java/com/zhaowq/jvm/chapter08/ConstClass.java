package com.zhaowq.jvm.chapter08;

/**
 * @author zhaowq
 * @Date 2018/9/19
 */
public class ConstClass {
    static {
        System.out.println("ConstClass init");
    }

    public static final String HELLOWORLD = "hello world";
}
