package com.example.demo.service.impl;

import com.example.demo.service.IUserService;

public class UserServiceImpl implements IUserService {
    @Override
    public Boolean login(String username, String password) {
        return "root".equals(username)&&"123456".equals(password);
    }
}
