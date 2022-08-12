package com.peng.controller;


import com.peng.common.lang.R;
import com.peng.entity.LeavingMessage;
import com.peng.entity.vo.LeavingMessageVo;
import com.peng.service.impl.LeavingMessageServiceImpl;
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
 * @since 2022-08-02
 */
@RestController
@RequestMapping("/api/leaving")
public class LeavingMessageController {
    @Resource
    LeavingMessageServiceImpl commentService;

    @PostMapping("/push")
    public R push(@Valid @RequestBody LeavingMessage leavingMessage){
        commentService.push(leavingMessage);
        return R.ok();
    }

    @GetMapping("/queryAll")
    public R queryAll(@RequestParam Integer goodsId){
        List<LeavingMessageVo> list= commentService.queryAll(goodsId);
        return R.ok(list);
    }
}
