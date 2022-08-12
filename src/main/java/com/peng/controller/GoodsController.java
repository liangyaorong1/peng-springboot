package com.peng.controller;


import cn.hutool.core.util.StrUtil;
import com.peng.common.lang.R;
import com.peng.entity.Goods;
import com.peng.entity.dto.GoodsDTO;
import com.peng.entity.query.GoodsQuery;
import com.peng.entity.vo.GoodsVo;
import com.peng.mapper.GoodsMapper;
import com.peng.service.impl.GoodsServiceImpl;
import com.peng.utils.CosUtil;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author peng
 * @since 2022-07-31
 */
@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    @Resource
    GoodsServiceImpl goodsService;
    @Resource
    GoodsMapper goodsMapper;

    @PostMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file) throws IOException {
        if(file.isEmpty()){
            throw new RuntimeException("上传文件不能为空");
        }
        CosUtil cosUtil=new CosUtil();
        String url=cosUtil.uploadFile(file);
        return R.ok(url);
    }

    @PostMapping("/sell")
    public R sell(@Valid @RequestBody GoodsDTO goodsDTO){
        if(StrUtil.isBlankIfStr(goodsDTO.getGoodsUrl())){
            throw new RuntimeException("请上传商品图片");
        }
        goodsService.sell(goodsDTO);
        return R.ok();
    }

    @PostMapping("/queryAll")
    public R queryAll(@RequestBody GoodsQuery goodsQuery){
        List<GoodsVo> list=goodsService.queryAll(goodsQuery);
        return R.ok(list);
    }

    @GetMapping("/queryItem")
    public R queryItem(@RequestParam Long id){
        GoodsVo goodsVo=goodsMapper.queryItem(id);
        return R.ok(goodsVo);
    }
}
