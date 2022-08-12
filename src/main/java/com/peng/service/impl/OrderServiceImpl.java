package com.peng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.peng.entity.Order;
import com.peng.mapper.OrderMapper;
import com.peng.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author peng
 * @since 2022-08-06
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Resource
    OrderMapper orderMapper;

    /**
     * 确定购买将生成订单
     * @param order
     */
    public void add(Order order){
        orderMapper.insert(order);
    }

    public List<Order> queryAll(Integer userId){
       return orderMapper.selectList(new LambdaQueryWrapper<Order>().eq(Order::getUserId,userId).orderByDesc(Order::getCreated));
    }


    @Override
    public int updateStatus(Long userId) {
        return orderMapper.updateStatus(userId);
    }
}
