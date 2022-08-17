package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.management.MBeanAttributeInfo;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class LoginController {
    private static Integer i = 0;
    Map sessionMap = new HashMap();
    ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();
    private static Object obj = new Object();

    @GetMapping("/login")
    public String login(@RequestParam String userName, @RequestParam String password) throws InterruptedException {
        if ("root".equals(userName) && "123456".equals(password)) {
            Integer sessionId = new Random().nextInt(100);
            User user = new User();
            user.setUsername(userName);
            user.setPassword(password);
            sessionMap.put(sessionId, user);
            return sessionId.toString();
        }
        return "登入失败";
    }

    @GetMapping("/toAdd")
    public String toAdd(@RequestParam int sessionId) throws InterruptedException {
        User user = (User) sessionMap.get(sessionId);
        threadLocal.set(user);
        User u = (User) threadLocal.get();
        return u.getUsername() + "," + u.getPassword();
    }

    /**
     * 测试synchrnoized
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/toThread")
    public Integer toThread() throws InterruptedException {
        synchronized (obj) {
            System.out.println(++i);
        }
    return i;
    }

    /**
     * 测试可重入锁
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/toReentrantLock")
    public Integer toReentrantLock() throws InterruptedException {
        synchronized (obj) {
            Integer i=M2();
        }
        return i;
    }

    public Integer M2() throws InterruptedException {
        synchronized (obj) {
           i++;
        }
        return i;
    }


//ThreadLocal使用场景：
    //1.DB要保存数据操作人用户信息（userId），你需要怎么做？sql怎么写？
    //答：用户登入系统成功后，把用户信息放到MAP(缓存)，key为sessionId,value为用户信息。然后返回sessionId.
    //当用户再进入其它操作（如新增）,通过SESSIONID,获取用户信息。然后把用户信息放到threadLocal中。

    //2.为什么要放到threadLocal中？我通过sessionId从缓存中获取用户信息,直接保存到数据表中,不就行了吗?
    //举个例子：一个线程，调用了10个方法，每个方法都要用到userId，或者前9个不需要userId传参，但最后一个需要userId传参。难道必须在每个方法中
    //都要传吗？
    // 如果不用threadLocal,就要在每个方法中，都要传userId.
    // 如果用threadLocal，就不用传userId，直接从threadLocal就取即可







}
