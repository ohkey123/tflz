package cn.shiyang.tflz.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class SecurityFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ObjectMapper om = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        map.put("isSucceed", false);
        map.put("msg", "登录失败！" + exception.getMessage());

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(om.writeValueAsString(map));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
