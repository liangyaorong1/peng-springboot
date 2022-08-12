package com.peng.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author peng
 * @create 2022-07-31 19:50
 */
@Data
public class GoodsVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer userId;

    private String nickName;

    private String goodsContent;

    private String goodsUrl;

    private Double goodsPrice;

    private Integer goodsNum;

    private String goodsAddress;

    private String goodsName;

    private Integer deleted;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private LocalDateTime created;
}
