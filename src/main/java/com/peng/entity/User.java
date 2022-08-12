package com.peng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author peng
 * @since 2022-07-30
 */
@Getter
@Setter
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @NotNull(message = "请输入邮箱")
    @TableField("username")
    private String username;
    @NotNull(message = "请输入密码")
    @TableField("password")
    private String password;
    @NotNull(message = "请输入昵称")
    @TableField("nick_name")
    private String nickName;

    @TableField("deleted")
    @TableLogic
    private Integer deleted;

    @TableField("perms")
    private String perms;


}
