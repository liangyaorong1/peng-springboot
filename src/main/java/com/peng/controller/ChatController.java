package com.peng.controller;


import com.peng.common.lang.R;
import com.peng.entity.Chat;
import com.peng.entity.vo.ChatVo;
import com.peng.mapper.ChatMapper;
import com.peng.service.impl.ChatServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author peng
 * @since 2022-08-04
 */
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Resource
    ChatServiceImpl chatService;
    @Resource
    ChatMapper chatMapper;

    @PostMapping("/saveSend")
    public R saveSend(@Valid @RequestBody Chat chat){
        chatService.saveSend(chat);
        return R.ok();
    }

    @GetMapping("/queryByCreated")
    public R queryByCreated(@RequestParam Integer goodsId){
        List<ChatVo> list=chatMapper.queryByCreated(goodsId);
        return R.ok(list);
    }

    @GetMapping("/queryAll")
    public R queryAll(@RequestParam Integer goodsId,@RequestParam String toName,@RequestParam String fromName){
        List<ChatVo> list=chatMapper.queryAll(goodsId,fromName,toName);
        return R.ok(list);
    }

    @GetMapping("/queryGroupByGoodsId")
    public R queryGroupByGoodsId(@RequestParam String name){
        List<ChatVo> list=chatMapper.queryGroupByGoodsId(name);
        return R.ok(list);
    }
}
