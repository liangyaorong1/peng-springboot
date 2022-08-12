package com.peng.service.impl;

import com.peng.entity.Chat;
import com.peng.mapper.ChatMapper;
import com.peng.service.IChatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author peng
 * @since 2022-08-04
 */
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements IChatService {

    @Resource
    ChatMapper chatMapper;

    public void saveSend(Chat chat){
        chatMapper.insert(chat);
    }
}
