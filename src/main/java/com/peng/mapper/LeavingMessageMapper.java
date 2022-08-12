package com.peng.mapper;

import com.peng.entity.LeavingMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peng.entity.vo.LeavingMessageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peng
 * @since 2022-08-02
 */
@Mapper
public interface LeavingMessageMapper extends BaseMapper<LeavingMessage> {
    List<LeavingMessageVo> queryAll(@Param("goodsId") Integer goodsId);
}
