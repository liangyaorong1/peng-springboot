package com.peng.service;

import com.peng.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author peng
 * @since 2022-08-06
 */
public interface IOrderService extends IService<Order> {
    int updateStatus(Long id);
}
