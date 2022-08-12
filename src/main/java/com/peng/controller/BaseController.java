package com.peng.controller;

import com.peng.utils.JwtUtils;
import com.peng.utils.RedisCache;
import com.peng.utils.RedisUtil;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class BaseController {
     @Resource
     HttpServletRequest req;
     @Resource
     RedisCache redisCache;

     @Resource
     JwtUtils jwtUtils;

     @Resource
     RestTemplate restTemplate;
}
