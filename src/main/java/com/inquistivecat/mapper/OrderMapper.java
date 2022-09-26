package com.inquistivecat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.inquistivecat.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hp
 */
@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}
