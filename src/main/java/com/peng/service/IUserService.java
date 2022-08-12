package com.peng.service;

import com.peng.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.peng.entity.dto.UserDTO;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author peng
 * @since 2022-07-30
 */
public interface IUserService extends IService<User> {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据用户编号获取用户的权限信息
     * @param userId
     * @return
     */
    List<String> findPermissions(Long userId);

    void forgetPass(UserDTO userDTO,@Param("code") String code);
}
