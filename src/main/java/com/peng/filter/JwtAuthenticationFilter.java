package com.peng.filter;

import cn.hutool.core.util.StrUtil;
import com.peng.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.TreeSet;

/**
 * jwt过滤器
 */
@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    private JwtUtils jwtUtils;
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        log.info("验证jwt.....");
        String jwt = request.getHeader(jwtUtils.getHeader());
        //判断是否为，null,空字符,Undefined
        if (StrUtil.isBlankOrUndefined(jwt)) {
            chain.doFilter(request, response);
            return;
        }
        Claims claims = jwtUtils.getClaimByToken(jwt);
        if (claims == null) {
            throw new JwtException("token 异常");
        }
        if (jwtUtils.isTokenExpired(claims)) {
            throw new JwtException("token已过期");
        }
        //获取当前用户信息
        String username = claims.getSubject();
        log.info("用户-{},正在登录！", username);
        //创建UsernamePasswordAuthenticationToken对象,将username封装成authentication传递到SecurityContextHolder进行管理
        /**
         * 获取到用户名之后我们直接把封装成UsernamePasswordAuthenticationToken，
         * 之后交给SecurityContextHolder参数传递authentication对象，
         * 这样后续security就能获取到当前登录的用户信息了，也就完成了用户认证。
         */
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, new TreeSet<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

}
