package com.peng.handler;


import cn.hutool.json.JSONUtil;
import com.peng.common.lang.Constants;
import com.peng.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登陆成功处理器
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();

        //生成jwt并返回
        /**
         * authentication.getName():获取当前用户信息
         */
        String jwt= jwtUtils.generateToken(authentication.getName());
        System.out.println(authentication.getName());
        response.setHeader(jwtUtils.getHeader(),jwt);
        Map<String,Object> map=new HashMap<>();
        map.put("msg", "Login successful and return token");
        map.put("code", Constants.OK_CODE);
        map.put("token",jwt);
        //R r=R.ok("登陆成功，并返回token",jwt);
        outputStream.write(JSONUtil.toJsonStr(map).getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }
}
