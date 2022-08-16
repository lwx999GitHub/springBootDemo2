package com.example.demo.test;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Task  implements Runnable {
    private String name;

    Task(String name) {
        this.name = name;
    }

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 1; i <= 4; i++) {
            // ThreadLocal.get方法获取本地线程变量
            if (null == ThreadLocalUtils.threadLocal.get()) {
                Random random = new Random();
                ThreadLocalUtils.threadLocal.set(random.nextInt(100));

                int num = (Integer) ThreadLocalUtils.threadLocal.get();
                System.out.println("线程:【" + name + "】当前值: " + num);

                TimeUnit.MILLISECONDS.sleep(1000);

                continue;

            }

            int num = (Integer) ThreadLocalUtils.threadLocal.get();
            num += 1;
            ThreadLocalUtils.threadLocal.set(num);

            System.out.println("线程:【" + name + "】当前值: " + num);
            if (i == 3) {
                ThreadLocalUtils.threadLocal.remove();
            }
            Thread.sleep(1000);

        }
    }
}
