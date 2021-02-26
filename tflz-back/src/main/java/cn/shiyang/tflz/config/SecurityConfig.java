package cn.shiyang.tflz.config;

import cn.shiyang.tflz.security.SecurityAuthenticationFilter;
import cn.shiyang.tflz.security.SecurityFailureHandler;
import cn.shiyang.tflz.security.SecurityLogoutSuccessHandler;
import cn.shiyang.tflz.security.SecuritySuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${spring.security.custom.login.page}")
    private String LOGIN_PAGE;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SecuritySuccessHandler securitySuccessHandler;

    @Autowired
    private SecurityFailureHandler securityFailureHandler;

    @Autowired
    private SecurityLogoutSuccessHandler securityLogoutSuccessHandler;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SecurityAuthenticationFilter authenticationFilter = new SecurityAuthenticationFilter();
        authenticationFilter.setAuthenticationManager(authenticationManager);
        authenticationFilter.setAuthenticationSuccessHandler(securitySuccessHandler);
        authenticationFilter.setAuthenticationFailureHandler(securityFailureHandler);

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.formLogin()
                .loginPage(LOGIN_PAGE);

        http.logout()
            .logoutSuccessHandler(securityLogoutSuccessHandler);

        http.authorizeRequests()
                .antMatchers(
                        // user authentication
                        "/tflz/user/register",

                        // swagger2
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v2/**",

                        // utils
                        "/tflz/utils/**",

                        // propose
                        "/tflz/propose/getList/**",
                        "/tflz/propose/getProposeDetails/**",

                        // game
                        "/tflz/game/listPlayer"
                ).permitAll()
                .anyRequest().authenticated();

        http.csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
