package com.peng.websocket;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author peng
 * @create 2022-08-03 22:40
 */
@Data
public class Message {
    private String time;
    private String to;//发送给哪个
    private String from;//发送方
    private String msg;//消息
    private Integer goodsId;//商品编号
    private List userNickNames;//登录昵称
}
