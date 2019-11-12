package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.Constant.MessageConstant;
import com.itheima.Constant.RedisConstant;
import com.itheima.mapping.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.ReportService;
import com.itheima.utils.QiniuUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 19:18 2019/11/11
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@RestController
@RequestMapping("/report")
public class ReportController {
    @Reference
    private ReportService reportService;

    @RequestMapping("/getMemberReport")
    public Result getMemberReport() {
        try {
            Map<String,Object> map = new HashMap();
            List<String> stringList = new ArrayList<>();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -12);
            for (int i = 0; i < 12; i++) {
                calendar.add(Calendar.MONTH, 1);
                stringList.add(new SimpleDateFormat("yyyy-MM").format(calendar.getTime()));
            }
            map.put("months", stringList);
            List<Integer> integerlist=new ArrayList<>();
            if (stringList != null && stringList.size() > 0) {
                for (String s : stringList) {
                    String months =s+"-31";
                    Integer count=reportService.getMemberReport(months);
                    integerlist.add(count);
                }
            }
            map.put("memberCount",integerlist);
            return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }
    @RequestMapping("/getSetmealReport")
    public Result getSetmealReport() {
        try {
            List<Map> setmealCount  = reportService.getSetmealReport();
            List<String>setmealNames=new ArrayList<>();
            for (Map map:setmealCount) {
                String name = (String) map.get("name");
                setmealNames.add(name);
            }
            Map<String,List>listMap=new HashMap<>();
            listMap.put("setmealCount",setmealCount);
            listMap.put("setmealNames",setmealNames);
            return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS, listMap);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_MEMBER_NUMBER_REPORT_FAIL);
        }
    }
    @RequestMapping("/exportBusinessReport")
    public Result exportBusinessReport() {
        try {

            return new Result(true, MessageConstant.GET_BUSINESS_REPORT_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
    }
}
