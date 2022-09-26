package com.inquistivecat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.inquistivecat.entity.OrderDetail;
import com.inquistivecat.mapper.OrderDetailMapper;
import com.inquistivecat.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * @author hp
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
