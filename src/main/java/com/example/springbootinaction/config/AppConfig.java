package com.example.springbootinaction.config;

import com.example.springbootinaction.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean(name = "userBean")
    public User initUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Hoxton");
        user.setNote("失業可憐人");
        return user;
    }
}
