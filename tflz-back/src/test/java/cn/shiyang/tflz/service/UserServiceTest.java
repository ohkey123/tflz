package cn.shiyang.tflz.service;

import cn.shiyang.tflz.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void test() {
        QueryWrapper<User> qw = new QueryWrapper<User>();
        qw.eq("email", "root@a.b");
        System.out.println(userService.getOne(qw));
    }
}