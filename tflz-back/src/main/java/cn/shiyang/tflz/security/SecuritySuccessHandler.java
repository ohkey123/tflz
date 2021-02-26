package cn.shiyang.tflz.security;

import cn.shiyang.tflz.dto.UserLoginSucceedResponseDTO;
import cn.shiyang.tflz.utils.UtilsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class SecuritySuccessHandler implements AuthenticationSuccessHandler {
    private final UtilsService utilsService;

    public SecuritySuccessHandler(UtilsService utilsService) {
        this.utilsService = utilsService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ObjectMapper om = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        map.put("isSucceed", true);

        String email = ((User) authentication.getPrincipal()).getUsername();

        UserLoginSucceedResponseDTO dto = utilsService.findUserThenToResponseDTO("email", email, true);

        map.put("userInfo", dto);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(om.writeValueAsString(map));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
