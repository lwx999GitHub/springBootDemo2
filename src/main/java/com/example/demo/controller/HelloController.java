package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/helloWorld")
    public String hello() throws InterruptedException {
        //Thread.sleep(1000);
        return ""+Thread.currentThread().getName();
    }
}
