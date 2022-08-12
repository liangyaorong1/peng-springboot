package com.peng.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2022-08-06
 */
@Getter
@Setter
@TableName("orders")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("userId")

    private Integer userId;
    @TableField("goodsId")
    private Integer goodsId;
    @TableField("seller")
    @NotNull(message = "卖家名称不能为空")
    private String seller;
    @NotNull(message = "订单名称不能为空")
    @TableField("order_name")
    private String orderName;
    @NotNull(message = "订单价格不能为空")
    @TableField("order_price")
    private Double orderPrice;
    @NotNull(message = "订单图片地址不能为空")
    @TableField("order_url")
    private String orderUrl;
    @NotNull(message = "订单数量不能为空")
    @TableField("order_num")
    private Integer orderNum;

    @TableField(value = "created", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private LocalDateTime created;
    @TableField("status")
    private Integer status;


}
