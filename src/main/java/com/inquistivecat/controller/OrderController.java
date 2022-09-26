package com.inquistivecat.controller;

import com.inquistivecat.commom.Result;
import com.inquistivecat.entity.Orders;
import com.inquistivecat.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hp
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/submit")
    public Result<String> submit(@RequestBody Orders orders){
        orderService.submit(orders);

        return Result.success("下单成功");
    }
}
