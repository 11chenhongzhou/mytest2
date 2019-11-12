package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.Constant.MessageConstant;
import com.itheima.mapping.Result;
import com.itheima.pojo.Menu;
import com.itheima.service.DemandMuEnService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 15:39 2019/11/11
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@RestController
@RequestMapping("/demandMuEn")
public class DemandMuEnController {
    @Reference
    private DemandMuEnService demandMuEnService;

    @RequestMapping("/MuEn")
    public Result findAllMuEn() {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        LinkedHashSet<Menu> menuList = null;
        try {
            menuList = demandMuEnService.findAllMuEn(username);
            return new Result(true, MessageConstant.GET_MENU_SUCCESS, menuList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_MENU_FAIL);
        }
    }
}
