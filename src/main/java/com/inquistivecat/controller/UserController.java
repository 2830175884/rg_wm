package com.inquistivecat.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.inquistivecat.commom.Result;
import com.inquistivecat.entity.User;
import com.inquistivecat.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author hp
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 发送手机验证码
     * @param user
     * @return
     */
    @PostMapping("/sendMsg")
    public Result<String> sendMsg(@RequestBody User user,HttpSession session){

        String phone = user.getPhone();
        if(!StringUtils.isNotEmpty(phone)){
            return Result.error("手机号码不能为空");
        }
        session.setAttribute(phone,"123456");

        return Result.success("发送成功");
    }

    @PostMapping("/login")
    public  Result<User> login(@RequestBody Map map, HttpSession session) {

        String phone = map.get("phone").toString();
//        String code = map.get("code").toString();
//        Object attribute = session.getAttribute(phone);
//        if(attribute != null ) {
//
//        }

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhone, phone);
        User one = userService.getOne(queryWrapper);
        if (one==null) {
            one = new User();
            one.setPhone(phone);
            userService.save(one);
        }
        session.setAttribute("user",one.getId());
        return Result.success(one);
//        return Result.error("登录失败");


    }




}
