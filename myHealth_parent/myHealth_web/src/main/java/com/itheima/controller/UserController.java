package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.Constant.MessageConstant;
import com.itheima.mapping.Result;
import com.itheima.pojo.Menu;
import com.itheima.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashSet;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 15:32 2019/11/11
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/getUserName")
    public Result findAllMuEn() {
        try {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = user.getUsername();
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS, username);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.GET_USERNAME_FAIL);
        }
    }
}
