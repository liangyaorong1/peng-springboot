package com.peng.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.lang.reflect.Member;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.peng.websocket.Message;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author peng
 * @since 2022-08-04
 */
@Getter
@Setter
@TableName("chat")
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotNull(message = "商品编号不能为空")
    @TableField("goodsId")
    private Integer goodsId;
    @NotNull(message = "接收用户不能为空")
    @TableField("from_name")
    private String fromName;
    @NotNull(message = "发送指定用户不能为空")
    @TableField("to_name")
    private String toName;
    @NotNull(message = "聊天内容不能为空")
    @TableField("msg")
    private String msg;
    @TableField(value = "created", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private LocalDateTime created;
    @NotNull(message = "聊天时间不能为空")
    @TableField("last_time")
    private String lastTime;
    @NotNull(message = "商品图片地址不能为空")
    @TableField("url")
    private String url;
    @NotNull(message = "用户编号地址不能为空")
    @TableField("userId")
    private Integer userId;



}
