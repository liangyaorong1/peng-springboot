package com.peng.controller;


import com.peng.common.lang.R;
import com.peng.entity.Order;
import com.peng.service.impl.OrderServiceImpl;
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
 * @since 2022-08-06
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Resource
    OrderServiceImpl orderService;

    @PostMapping("/add")
    public R add(@Valid @RequestBody Order order){
        orderService.add(order);
        return R.ok();
    }

    @GetMapping("/queryAll")
    public R add(@RequestParam Integer userId){
        List<Order> list=orderService.queryAll(userId);
        return R.ok(list);
    }
    @PutMapping("/updateStatus")
    public R updateStatus(@RequestParam Long id){
        orderService.updateStatus(id);
        return R.ok();
    }
}
