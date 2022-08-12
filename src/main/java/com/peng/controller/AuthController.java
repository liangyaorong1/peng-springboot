package com.peng.controller;

import com.peng.common.lang.R;
import com.peng.entity.User;
import com.peng.entity.dto.UserDTO;
import com.peng.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author peng
 * @create 2022-07-30 04:34
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController extends BaseController {
    @Resource
    UserServiceImpl userService;
    @GetMapping("/sendEmail")
    public R sendEmail(@RequestParam String email){
        userService.sendSimpleMail(email);
        return R.ok();
    }

    @PostMapping("/register")
    public R register(@RequestBody UserDTO userDTO,@RequestParam String code){
        userService.register(userDTO,code);
        return R.ok();
    }

    @PostMapping("/forgetPass")
    public R forgetPass(@RequestBody UserDTO userDTO, @RequestParam String code){
        userService.forgetPass(userDTO,code);
        return R.ok();
    }

    @GetMapping("/sendFeedback")
    public R sendFeedback(@RequestParam String email,@RequestParam String content){
        userService.sendFeedback(email,content);
        return R.ok();
    }
}
