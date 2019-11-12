package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.Constant.MessageConstant;
import com.itheima.mapping.Result;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetMealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 21:35 2019/11/4
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@RestController
@RequestMapping("/SetMeal")
public class SetMealController {
    @Reference
    private SetMealService setMealService;

    @RequestMapping("/getSetMeal")
    public Result getSetMeal() {
        try {
            List<Setmeal> setMealList = setMealService.getSetMeal();
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,setMealList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            Setmeal setmeal = setMealService.findByIdMobile(id);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,setmeal);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }
}
