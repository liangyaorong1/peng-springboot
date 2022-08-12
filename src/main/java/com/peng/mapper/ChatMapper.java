package com.peng.mapper;

import com.peng.entity.Chat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peng.entity.vo.ChatVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peng
 * @since 2022-08-04
 */
@Mapper
public interface ChatMapper extends BaseMapper<Chat> {
    /**
     * 查询指定商品的消息
     * @param goodsId
     * @return
     */
    List<ChatVo> queryByCreated(@Param("goodsId") Integer goodsId);

    List<ChatVo> queryAll(@Param("goodsId") Integer goodsId,@Param("fromName") String fromName,@Param("toName") String toName);

    List<ChatVo> queryGroupByGoodsId(@Param("name") String name);
}
