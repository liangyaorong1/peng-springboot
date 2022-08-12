package com.peng.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.peng.common.lang.R;
import com.peng.entity.User;
import com.peng.entity.dto.UserDTO;
import com.peng.mapper.UserMapper;
import com.peng.service.IUserService;
import com.peng.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author peng
 * @since 2022-07-30
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    UserMapper userMapper;
    @Resource
    UserServiceImpl userService;

    /**
     * 获取登录用户信息
     * @param principal
     * @return
     */
    @GetMapping("/userInfo")
    public R userInfo(Principal principal){
        String username=principal.getName();
        User user=userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername,username));
        return R.ok(user);
    }


}
