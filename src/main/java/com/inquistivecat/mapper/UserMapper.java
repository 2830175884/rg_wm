package com.inquistivecat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.inquistivecat.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author jh
 *
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
