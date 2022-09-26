package com.inquistivecat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.inquistivecat.entity.ShoppingCart;
import com.inquistivecat.mapper.ShoppingCartMapper;
import com.inquistivecat.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * @author jh
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
