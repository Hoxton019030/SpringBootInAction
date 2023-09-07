package com.example.springbootinaction.controller;

import com.example.springbootinaction.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final RedisTemplate<String, User> redisTemplate;

    @PostMapping()
    public void save(@RequestBody User user) {
        redisTemplate.opsForValue().set("user", user);
    }

    @GetMapping("{key}")
    public User get(@PathVariable("key") String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @DeleteMapping("delete/{key}")
    public Boolean delete(@PathVariable("key") String key) {
        return redisTemplate.delete(key);
    }

    @GetMapping("list")
    public List<User> listTest() {
        ListOperations<String, User> stringUserListOperations = redisTemplate.opsForList();
        stringUserListOperations.leftPush("list", new User());
        stringUserListOperations.leftPush("list", new User());
        stringUserListOperations.leftPush("list", new User());
        return stringUserListOperations.range("list", 0, 2);
    }

    @GetMapping("set")
    public Set<User> setTest() {
        SetOperations<String, User> stringUserSetOperations = redisTemplate.opsForSet();
        User hoxton = new User();
        hoxton.setUsername("Hoxton");
        User yiwen = new User();
        yiwen.setUsername("Selime");
        stringUserSetOperations.add("set", hoxton);
        stringUserSetOperations.add("set", hoxton);
        stringUserSetOperations.add("set", yiwen);
        stringUserSetOperations.add("set", yiwen);
        Set<User> set = stringUserSetOperations.members("set");
        return set;
    }

    /**
     * 有序集合
     *
     * @return
     */
    @GetMapping("zset")
    public Set<User> zSetTest() {
        ZSetOperations<String, User> stringUserZSetOperations = redisTemplate.opsForZSet();
        User hoxton1 = new User();
        hoxton1.setUsername("Hoxton");
        hoxton1.setId(1L);
        User hoxton2 = new User();
        hoxton2.setUsername("Hoxton");
        hoxton2.setId(2L);
        User hoxton3 = new User();
        hoxton3.setUsername("Hoxton");
        hoxton3.setId(3L);
        stringUserZSetOperations.add("zset", hoxton3, 3);
        stringUserZSetOperations.add("zset", hoxton1, 1);
        stringUserZSetOperations.add("zset", hoxton2, 2);
        Set<User> zset = stringUserZSetOperations.range("zset", 0, 2);
        return zset;
    }

    @GetMapping("hash")
    public User hashTest() {
        HashOperations<String, Object, User> hash = redisTemplate.opsForHash();
        hash.put("key", "hashkey", new User(1L));
        return hash.get("key", "hashkey");

    }
}
