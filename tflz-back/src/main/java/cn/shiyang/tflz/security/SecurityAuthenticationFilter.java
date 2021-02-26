package cn.shiyang.tflz.security;

import cn.shiyang.tflz.dto.UserLoginRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private boolean postOnly = true;

    public SecurityAuthenticationFilter() {
        /**
         * 设置该过滤器对POST请求/login进行拦截
         */
        super(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            /**
             * 从http请求中获取用户输入的用户名和密码信息
             * 这里接收的是form形式的参数，如果要接收json形式的参数，修改这里即可
             */
            ObjectMapper om = new ObjectMapper();
            UserLoginRequestDTO dto = null;
            try {
                dto = om.readValue(request.getInputStream(), UserLoginRequestDTO.class);
            } catch (IOException e) {
                throw new UsernameNotFoundException("SecurityAuthenticationFilter获取用户认证信息失败");
            }
            /**
             * 校验验证码
             */
            String input_checkCode = dto.getCheckCode();
            if (input_checkCode == null || input_checkCode.trim().length() == 0) {
                throw new InsufficientAuthenticationException("验证码不能为空！");
            }
            if (request.getSession().getAttribute("checkCode") == null) {
                throw new AuthenticationServiceException("服务器错误...");
            }
            if (input_checkCode.toLowerCase().compareTo(((String) request.getSession().getAttribute("checkCode")).toLowerCase()) != 0) {
                throw new InsufficientAuthenticationException("验证码错误！");
            }
            String email = dto.getEmail();
            String password = dto.getPassword();
            if (StringUtils.isEmpty(email) && StringUtils.isEmpty(password)) {
                throw new UsernameNotFoundException("请检查您的用户名或密码");
            }
            /**
             * 使用用户输入的用户名和密码信息创建一个未认证的用户认证Token
             */
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, password);
            /**
             * 设置一些详情信息
             */
            this.setDetails(request, authRequest);
            /**
             * 通过AuthenticationManager调用相应的AuthenticationProvider进行用户认证
             */
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }
}
