package com.peng.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author peng
 * @create 2022-08-02 00:25
 */
@Data
public class LeavingMessageVo implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long id;
    private String nickName;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private LocalDateTime created;
}
