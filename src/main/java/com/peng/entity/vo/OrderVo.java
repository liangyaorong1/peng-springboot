package com.peng.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class OrderVo implements Serializable {


    private Long id;



    private Integer userId;



    private Integer goodsId;

    private String orderName;


    private Double orderPrice;


    private String orderUrl;


    private Integer orderNum;


    private LocalDateTime created;


    private Integer status;


    private String orderContent;
}
