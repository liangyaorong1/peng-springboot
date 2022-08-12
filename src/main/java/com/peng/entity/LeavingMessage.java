package com.peng.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 
 * </p>
 *
 * @author peng
 * @since 2022-08-02
 */
@Getter
@Setter
@TableName("leaving_message")
public class LeavingMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("userId")
    private Integer userId;

    @TableField("goodsId")
    private Integer goodsId;

    @TableField("content")
    @NotEmpty(message = "评论内容不能为空")
    private String content;

    @TableField(value = "created", fill = FieldFill.INSERT)
    private LocalDateTime created;


}
