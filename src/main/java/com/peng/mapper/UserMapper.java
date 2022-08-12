package com.peng.mapper;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.peng.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peng.entity.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peng
 * @since 2022-07-30
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 通过角色与权限的关联表的用户编号获取呀到用户权限集合
     * @param userId
     * @return
     */
    List<String> findPermissions(@Param(value = "id") Long userId);

    void forgetPass(UserDTO userDTO);
}
