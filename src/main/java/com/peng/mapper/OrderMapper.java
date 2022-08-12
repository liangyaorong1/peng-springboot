package com.peng.mapper;

import com.peng.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peng
 * @since 2022-08-06
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    int updateStatus(@Param("id") Long id);
}
