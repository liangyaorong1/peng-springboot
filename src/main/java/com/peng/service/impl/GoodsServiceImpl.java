package com.peng.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.peng.entity.Goods;
import com.peng.entity.dto.GoodsDTO;
import com.peng.entity.query.GoodsQuery;
import com.peng.entity.vo.GoodsVo;
import com.peng.mapper.GoodsMapper;
import com.peng.service.IGoodsService;
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
 * @since 2022-07-31
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
    @Resource
    GoodsMapper goodsMapper;

    public void sell(GoodsDTO goodsDTO){
        if(StrUtil.isBlankIfStr(goodsDTO)){
            throw new RuntimeException("参数不能为空");
        }
        goodsMapper.insert(goodsDTO);
    }

    public List<GoodsVo> queryAll(GoodsQuery goodsQuery){
//        LambdaQueryWrapper<Good> wrapper=new LambdaQueryWrapper();
//
//        List<Good> list=goodMapper.selectList(new LambdaQueryWrapper<Good>().orderByDesc(Good::getCreated));
        List<GoodsVo> list= goodsMapper.queryAll(goodsQuery);
        return list;
    }


//    public Goods queryItem(Long id){
//        return goodsMapper.selectOne(new LambdaQueryWrapper<Goods>().eq(Goods::getId,id));
//    }
}
