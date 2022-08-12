package com.peng.service.impl;

import com.peng.entity.LeavingMessage;
import com.peng.entity.vo.LeavingMessageVo;
import com.peng.mapper.LeavingMessageMapper;
import com.peng.service.ILeavingMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author peng
 * @since 2022-08-02
 */
@Service
public class LeavingMessageServiceImpl extends ServiceImpl<LeavingMessageMapper, LeavingMessage> implements ILeavingMessageService {
    @Resource
    LeavingMessageMapper leavingMessageMapper;


    public void push(LeavingMessage leavingMessage){
        leavingMessageMapper.insert(leavingMessage);
    }

    @Override
    public List<LeavingMessageVo> queryAll(Integer goodsId) {
        List<LeavingMessageVo> list= leavingMessageMapper.queryAll(goodsId);
        return list;
    }
}
