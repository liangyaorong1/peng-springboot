package com.peng.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.peng.common.lang.Constants;
import com.peng.entity.User;
import com.peng.entity.dto.UserDTO;
import com.peng.mapper.UserMapper;
import com.peng.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peng.utils.RedisCache;
import com.peng.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.tools.JavaFileManager;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author peng
 * @since 2022-07-30
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    UserMapper userMapper;
    @Value("${spring.mail.username}")
    String from;

    @Resource
    RedisCache redisCache;
    @Resource
    JavaMailSender javaMailSender;


    @Resource
    @Lazy
    PasswordEncoder encoder;

    /**
     * 查询当前用户信息
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        return getOne(new QueryWrapper<User>().eq("username",username));
    }

    /**
     * 查询当前用户权限
     * @param userId
     * @return
     */
    @Override
    public List<String> findPermissions(Long userId) {
        if(StrUtil.isBlankIfStr(userId)){
            throw new RuntimeException("查询权限失败");
        }
        return userMapper.findPermissions(userId);
    }



    /**
     * 发送邮件验证码
     * @param to 发送到哪个用户邮箱
     * @param title 标题
     * @param content 内容
     */
    public void sendSimpleMail(String email){
        if(StrUtil.isBlankIfStr(email)){
            throw new RuntimeException("Please fill in the user email");
        }
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSentDate(new Date());
        message.setSubject("Registered mailbox verification");
        String code= RandomUtil.randomNumbers(4);//随机生成一个4位长度的验证码
        message.setText("Verification code of your email registration:"+code+"The validity period is 5 minutes. Please keep it properly and do not leak it");
        redisCache.setCacheObject(Constants.EMAIL_KEY,code);
        javaMailSender.send(message);
        log.info("邮件发送成功");
    }

    /**
     * 发送反馈意见
     * @param email
     * @param content
     */
    public void sendFeedback(String email,String content){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSentDate(new Date());
        message.setSubject("Feedback");
        message.setText(content);
        javaMailSender.send(message);
    }

    /**
     * 注册用户
     * @param userDTO
     * @param code
     */
    public void register(UserDTO userDTO,String code){
        if(StrUtil.isBlankIfStr(code)){
            throw new RuntimeException("请发送验证码");
        }
        if(!code.equals(redisCache.getCacheObject(Constants.EMAIL_KEY))){//输入的验证码与邮箱发送的验证码不同则返回错误信息
            throw new RuntimeException("验证码错误");
        }
        if(userMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getUsername,userDTO.getUsername()))>=1){
            throw new RuntimeException("该邮箱已被注册,请重新输入");
        }
        if(userMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getNickName,userDTO.getNickName()))>=1){
            throw new RuntimeException("该昵称已被占用，请重新输入");
        }
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        userDTO.setPerms("user");//默认注册为买家
        userMapper.insert(userDTO);
    }

    @Override
    public void forgetPass(UserDTO userDTO,String code){
        if(StrUtil.isBlankIfStr(code)){
            throw new RuntimeException("请发送验证码");
        }
        if(!code.equals(redisCache.getCacheObject(Constants.EMAIL_KEY))){//输入的验证码与邮箱发送的验证码不同则返回错误信息
            throw new RuntimeException("验证码错误");
        }
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        userMapper.forgetPass(userDTO);
    }
}
