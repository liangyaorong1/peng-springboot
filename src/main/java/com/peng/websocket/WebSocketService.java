package com.peng.websocket;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.peng.entity.Chat;
import com.peng.mapper.ChatMapper;
import com.peng.service.impl.ChatServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author peng
 * @create 2022-08-03 22:06
 */
@Component
@ServerEndpoint(value = "/api/socket/{nickName}")
public class WebSocketService {



    private static final Logger log= LoggerFactory.getLogger(WebSocketService.class);

    /**
     * 记录当前在线连接数
     */
    public static final Map<String, Session> SESSION_MAP=new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("nickName") String nickName){
        SESSION_MAP.put(nickName,session);
        log.info("有新用户加入,nickName={},当前在线人数:{}",nickName,SESSION_MAP.size());
        sendMessage(setUserList(),session);
    }

    /**
     * 接收处理客户端发来的数据
     * @param message
     */
    @OnMessage
    public void onMessage(String message){
        Message msg=JSON.parseObject(message,Message.class);//解析消息为java对象
        String to=msg.getTo();
        Session toSession=SESSION_MAP.get(to);
        if(toSession!=null){
            sendMessage(JSON.toJSONString(msg),toSession);
            log.info("发送给用户nickName={},消息:{}",msg.getTo(),JSON.toJSONString(msg));
        }else{
            log.info("发送失败,未找到用户nickName={}的session",msg.getTo());
        }
    }

    @OnError
    public void onError(Session session,Throwable error){
        log.info("发送失败");
        error.printStackTrace();
    }

    /**
     * 把进行连接的用户从SESSION_MAP中移除
     * @param session
     * @param nickName
     */
    @OnClose
    public void onClose(Session session,@PathParam("nickName") String nickName){
        SESSION_MAP.remove(nickName);
        log.info("有一处连接关闭，移除nickName={}用户的session,当前在线人数:{}",nickName,SESSION_MAP.size());
    }

    private String setUserList(){
        List list=new ArrayList<>();
        for(String key:SESSION_MAP.keySet()){
            JSONObject jsonObject=new JSONObject();
            jsonObject.set("nickName",key);
            list.add(jsonObject);
        }
        Message message=new Message();
        message.setUserNickNames(list);
        return JSON.toJSONString(message);
    }

    /**
     * 服务器发送消息给指定客户端
     * @param message
     * @param toSession
     */
    private void sendMessage(String message,Session toSession){
        try {
            toSession.getBasicRemote().sendText(message);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
