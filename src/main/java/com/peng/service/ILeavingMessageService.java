package com.peng.service;

import com.peng.entity.LeavingMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.peng.entity.vo.LeavingMessageVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author peng
 * @since 2022-08-02
 */
public interface ILeavingMessageService extends IService<LeavingMessage> {
    List<LeavingMessageVo> queryAll(Integer goodsId);
}
