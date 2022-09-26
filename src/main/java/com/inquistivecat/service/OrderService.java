package com.inquistivecat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.inquistivecat.entity.Orders;

/**
 * @author hp
 */
public interface OrderService extends IService<Orders> {

    /**
     * 用户下单
     * @param order
     */
    public void submit(Orders order);
}
