package com.peng.mapper;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.peng.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peng.entity.query.GoodsQuery;
import com.peng.entity.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peng
 * @since 2022-07-31
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    List<GoodsVo> queryAll(@Param(Constants.WRAPPER) GoodsQuery goodsQuery);

    GoodsVo queryItem(@Param("id")Long id);
}
