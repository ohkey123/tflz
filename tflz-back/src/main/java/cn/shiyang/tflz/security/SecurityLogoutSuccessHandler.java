package cn.shiyang.tflz.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class SecurityLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ObjectMapper om = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        map.put("isSucceed", true);

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(om.writeValueAsString(map));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
