package com.peng.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author peng
 * @since 2022-07-31
 */
@Getter
@Setter
@TableName("goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("userId")
    private Integer userId;
    @NotNull(message = "商品介绍不能为空")
    @TableField("goods_content")
    private String goodsContent;
    @NotNull(message = "商品图片不能为空")
    @TableField("goods_url")
    private String goodsUrl;
    @NotNull(message = "商品价格不能为空")
    @TableField("goods_price")
    private Double goodsPrice;
    @NotNull(message = "商品数量不能为空")
    @TableField("goods_num")
    private Integer goodsNum;
    @NotNull(message = "商品交易地址不能为空")
    @TableField("goods_address")
    private String goodsAddress;
    @NotNull(message = "商品名称地址不能为空")
    @TableField("goods_name")
    private String goodsName;

    @TableField("deleted")
    @TableLogic
    private Integer deleted;

    @TableField(value = "created", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private LocalDateTime created;


}
