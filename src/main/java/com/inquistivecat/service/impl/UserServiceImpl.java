package com.inquistivecat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.inquistivecat.entity.User;
import com.inquistivecat.mapper.UserMapper;
import com.inquistivecat.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author jh
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
