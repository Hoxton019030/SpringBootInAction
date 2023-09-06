package com.example.springbootinaction;

import com.example.springbootinaction.config.AppConfig;
import com.example.springbootinaction.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


@SpringBootTest
@Slf4j
public class IocTest {

    @Test
    void userBeanTest() {
        ApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        User user = annotationConfigApplicationContext.getBean(User.class);
        log.info("userId: " + user.getId());
    }

}
