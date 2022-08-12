package com.peng.handler;


import cn.hutool.json.JSONUtil;
import com.peng.common.lang.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆失败处理器
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
        res.setContentType("application/json;charset=UTF-8");
        ServletOutputStream stream=res.getOutputStream();
        R r=R.fail("Bad credentials".equals(e.getMessage()) ? "Wrong user name or password" :e.getMessage());
        stream.write(JSONUtil.toJsonStr(r).getBytes("UTF-8"));
        stream.flush();
        stream.close();
    }
}
