package cn.shiyang.tflz.controller;

import cn.shiyang.tflz.dto.UserLoginSucceedResponseDTO;
import cn.shiyang.tflz.dto.UserRegisterRequestDTO;
import cn.shiyang.tflz.entity.Notice;
import cn.shiyang.tflz.entity.User;
import cn.shiyang.tflz.service.NoticeService;
import cn.shiyang.tflz.service.UserService;
import cn.shiyang.tflz.utils.UtilsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author shiyang
 * @since 2021-01-25
 */
@RestController
@RequestMapping("/tflz/user")
@Api("用户前端控制器")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private NoticeService noticeService;

    // Spring Security 实现注册后自动登录
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UtilsService utilsService;

    @PostMapping("/getUserInfo/{uid}")
    @ApiOperation("用于已登录的用户更新信息")
    public UserLoginSucceedResponseDTO getUserInfo(@PathVariable("uid")
                                                   @Valid @Min(value = 1, message = "用户id不能小于1") Integer id) {
        return utilsService.findUserThenToResponseDTO("id", String.valueOf(id), true);
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Map register(@RequestBody @Valid UserRegisterRequestDTO userRegisterRequestDTO, HttpSession session, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        // 检查验证码
        if (!utilsService.verifyCheckCode(map, userRegisterRequestDTO.getCheckCode(), (String) session.getAttribute("checkCode"))) {
            return map;
        }

        String email = userRegisterRequestDTO.getEmail();
        List<String> fli = Arrays.asList("a@ab.xyz", "b@ab.xyz",
                "c@ab.xyz", "d@ab.xyz", "e@ab.xyz", "f@ab.xyz",
                "1158503631@qq.com", "994098566@qq.com",
                "961169599@qq.com", "1368586597@qq.com",
                "1946695129@qq.com", "1430171363@qq.com",
                "1310228190@qq.com");
        boolean flag = false;
        for (String s : fli) {
            if (email.trim().toLowerCase().compareTo(s) == 0) {
                flag = true;
                break;
            }
        }
        if (flag == false) {
            map.put("isSucceed", false);
            map.put("msg", "您不是内测用户！（项目组成员请使用自己的QQ号登录）！");
            return map;
        }

        // 注册逻辑
        User user = new User();
        QueryWrapper<User> qw = new QueryWrapper<>();

        qw.eq("email", userRegisterRequestDTO.getEmail());
        if (userService.count(qw) > 0) {
            map.put("isSucceed", false);
            map.put("msg", "该电子邮箱已经注册过账号！");
            return map;
        }
        user.setEmail(userRegisterRequestDTO.getEmail());

        qw.clear();
        qw.eq("name", userRegisterRequestDTO.getName());
        if (userService.count(qw) > 0) {
            map.put("isSucceed", false);
            map.put("msg", "该用户名已存在！");
            return map;
        }
        user.setName(userRegisterRequestDTO.getName());

        user.setAge(userRegisterRequestDTO.getAge());
        user.setSex(userRegisterRequestDTO.getSex());
        user.setPassword(passwordEncoder.encode(userRegisterRequestDTO.getPassword()));
        Date date = new Date();
        user.setTimeCreate(date);
        user.setTimeModify(date);

        boolean isSave = userService.save(user);
        if (isSave == false) {
            map.put("isSucceed", false);
            map.put("msg", "注册失败！请检查您填写的数据是否合法");
            return map;
        }

        map.put("isSucceed", true);

        // 注册成功后给前端返回DTO对象
        UserLoginSucceedResponseDTO dto = utilsService.findUserThenToResponseDTO("email", user.getEmail(), true);

        // 注册后自动登录

        // 将用户名，密码生成认证可用的AuthenticationToken, loginName 用户名，loginName 密码
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userRegisterRequestDTO.getEmail(), userRegisterRequestDTO.getPassword());
        // 设置authenticationToken的Details，主要获取当前请求的一些信息
        authenticationToken.setDetails(new WebAuthenticationDetails(request));
        // 使用authenticationManager 接口中的 authenticate 进行 SpringSecurity 认证
        Authentication authenticatedUser = authenticationManager.authenticate(authenticationToken);
        // 设置当前认证对象
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        // 如果没有session，生成一个session并设置当前的SecurityContext
        request.getSession().setAttribute(HttpSessionSecurityContextRepository
                .SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());


        map.put("userInfo", dto);
        return map;
    }
}

