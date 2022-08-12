package com.peng.config;

import com.peng.filter.JwtAuthenticationFilter;
import com.peng.handler.JwtAuthenticationEntryPoint;
import com.peng.handler.JwtLogoutSuccessHandler;
import com.peng.handler.LoginFailureHandler;
import com.peng.handler.LoginSuccessHandler;
import com.peng.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Security配置文件
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //白名单
    public static final String[] URL_WHITELIST = {
            "/webjars/**",
            "/favicon.ico",
            "/api/auth/**",
            "/api/goods/queryAll",
            "/api/leaving/queryAll",
            "/api/goods/queryItem",
            "/api/socket/**",
            "/login",
            "/logout"
    };

    /**
     * 登陆成功处理器
     */
    @Resource
    LoginSuccessHandler loginSuccessHandler;
    /**
     * 登陆失败处理器
     */
    @Resource
    LoginFailureHandler loginFailureHandler;
   @Resource
   JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

   @Resource
    JwtLogoutSuccessHandler jwtLogoutSuccessHandler;
   @Resource
   UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(authenticationManager());
        return filter;
    }
    // 创建BCryptPasswordEncoder注入spring容器
    @Bean
    PasswordEncoder passwordEncoder() {   return new BCryptPasswordEncoder();}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //允许跨域访问
        http.cors()
                .and()
                //开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
                .csrf().disable()
                //设置登陆页
                .formLogin()
                //登陆失败处理器
                .failureHandler(loginFailureHandler)
                //登陆成功处理器，并返回token
                .successHandler(loginSuccessHandler)
                .and()
                .logout()
                .logoutSuccessHandler(jwtLogoutSuccessHandler)
                .and()
                //当出现跨域的OPTIONS请求时，发现被拦截，加入下面设置可实现对OPTIONS请求的放行
                .authorizeRequests()
                .antMatchers(URL_WHITELIST).permitAll()//放行白名单
                //表示所有的访问都必须认证，认证处理后才可以正常进行
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .addFilter(jwtAuthenticationFilter());
    }
}
