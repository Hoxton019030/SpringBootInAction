package com.example.springbootinaction.service;

import com.example.springbootinaction.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Cacheable(value = "test")
    public User getUser(){
        System.out.println("沒有被快取");
        User user = new User();
        user.setUsername("Hoxton");
        user.setNote("失業男子");
        user.setId(1L);
        return user;
    }
}
