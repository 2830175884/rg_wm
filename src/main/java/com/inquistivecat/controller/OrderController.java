package com.inquistivecat.controller;

import com.inquistivecat.commom.Result;
import com.inquistivecat.entity.Orders;
import com.inquistivecat.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author hp
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 提交订单并保存
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public Result<String> submit(@RequestBody Orders orders){
        orderService.submit(orders);

        return Result.success("下单成功");
    }

    /**
     * 分页查询订单信息
     * @param page
     * @param pageSize
     * @param number
     * @param beginTime
     * @param endTime
     * @return
     */
    @GetMapping("page")
    public Result<Page> page(int page,int pageSize,String number,String beginTime,String endTime){
        return Result.error("接口还未完成");
    }
}
