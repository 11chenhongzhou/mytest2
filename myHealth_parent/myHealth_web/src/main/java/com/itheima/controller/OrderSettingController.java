package com.itheima.controller;

import java.util.HashMap;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.Constant.MessageConstant;
import com.itheima.mapping.Result;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import com.itheima.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 11:14 2019/11/4
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@RestController
@RequestMapping("/OrderSetting")
public class OrderSettingController {
    @Reference
    private OrderSettingService orderSettingService;

    //新增
    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile excelFile) {
        try {
            //读取Excel文件数据
            List<String[]> list = POIUtils.readExcel(excelFile);
            if (list != null && list.size() > 0) {
                List<OrderSetting> orderSettingList = new ArrayList<>();
                for (String[] strings : list) {
                    OrderSetting orderSetting = new OrderSetting();
                    orderSetting.setOrderDate(new Date(strings[0]));
                    orderSetting.setReservations(Integer.parseInt(strings[1]));
                    orderSettingList.add(orderSetting);
                }
                orderSettingService.upload(orderSettingList);
            }
        } catch (Exception e) {
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        }
        return new Result(true, MessageConstant.IMPORT_ORDERSETTING_FAIL);
    }

    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String currentYear, Integer currentMonth) {
        try {
            String Month=currentMonth+"";
            if (currentMonth<10&&currentMonth>0){
                Month="0"+currentMonth;
            }
            String currentYearAndMonth = "%" + currentYear + "-" +Month+ "%";
            List<OrderSetting> orderSettingList = orderSettingService.getOrderSettingByMonth(currentYearAndMonth);
            List<Map> orderSettingMap = new ArrayList<>();
            for (OrderSetting orderSetting : orderSettingList) {
                Map map = new HashMap();
                map.put("date", orderSetting.getOrderDate().getDate());
                map.put("number", orderSetting.getNumber());
                map.put("reservations", orderSetting.getReservations());
                orderSettingMap.add(map);
            }
            return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS, orderSettingMap);
        } catch (Exception e) {
            return new Result(false, MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }

    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting) {
        try {
            orderSettingService.editNumberByDate(orderSetting);
            return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            return new Result(false, MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }
}
