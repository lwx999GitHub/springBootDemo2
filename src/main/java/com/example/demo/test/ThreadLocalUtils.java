package com.example.demo.test;

public class ThreadLocalUtils {

    public static final ThreadLocal<Object> threadLocal = new ThreadLocal<Object>() {
        @Override
        protected Object initialValue() {
            System.out.println("调用get()方法，当前线程共享变量未设置，自动调用initialValue()方法");
//            return new Random().nextInt(100);
            return null;
        }
    };
}
