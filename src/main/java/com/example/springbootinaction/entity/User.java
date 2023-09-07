package com.example.springbootinaction.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class User implements Serializable {
    private Long id;
    private String username;
    private String note;
}
