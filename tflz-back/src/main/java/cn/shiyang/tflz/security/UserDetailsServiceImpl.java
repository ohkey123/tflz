package cn.shiyang.tflz.security;

import cn.shiyang.tflz.entity.User;
import cn.shiyang.tflz.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (s == null || s.length() == 0) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        QueryWrapper<User> qw = new QueryWrapper<User>();
        qw.eq("email", s);
        User user = userService.getOne(qw);
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return new org.springframework.security.core.userdetails.User(s, user.getPassword(), Collections.emptyList());
    }
}
