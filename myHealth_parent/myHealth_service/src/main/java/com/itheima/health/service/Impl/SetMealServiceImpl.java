package com.itheima.health.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.OrderDao;
import com.itheima.dao.OrderSettingDao;
import com.itheima.dao.SetMealDao;
import com.itheima.mapping.PageResult;
import com.itheima.mapping.QueryPageBean;
import com.itheima.pojo.OrderSetting;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetMealService;
import com.itheima.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 19:43 2019/11/3
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@Service
public class SetMealServiceImpl implements SetMealService {
    @Autowired
    private SetMealDao setMealDao;
    @Autowired
    private OrderDao orderDao;
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<Setmeal> setmealPage=setMealDao.findPage(queryPageBean.getQueryString());
        return new PageResult(setmealPage.getTotal(),setmealPage.getResult());
    }

    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setMealDao.addSetmel(setmeal);
        setmealcheckgroup(setmeal.getId(),checkgroupIds);
    }

    @Override
    public Setmeal findById(Integer id) {
        return setMealDao.findById(id);
    }

    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return setMealDao.findCheckItemIdsByCheckGroupId(id);
    }

    @Override
    public List<Setmeal> getSetMeal() {
        return setMealDao.getSetMeal();
    }

    @Override
    public Setmeal findByIdMobile(Integer id) {
        return setMealDao.findByIdMobile(id);
    }

    @Override
    public Map getBusinessReportData() throws Exception {
        //获得当前日期
        String today = DateUtils.parseDate2String(DateUtils.getToday());
        //获得本周一的日期
        String thisWeekMonday = DateUtils.parseDate2String(DateUtils.getThisWeekMonday());
        //获得本月第一天的日期
        String firstDay4ThisMonth = DateUtils.parseDate2String(DateUtils.getFirstDay4ThisMonth());
        //今日新增会员数
        Integer todayNewMember = setMealDao.todayNewMember(today);
        //总会员数
        Integer totalMember = setMealDao.totalMember();
        //本周新增会员数
        Integer thisWeekNewMember=setMealDao.thisWeekNewMember(thisWeekMonday);
        //本月新增会员数
        Integer thisMonthNewMember=setMealDao.thisMonthNewMember(firstDay4ThisMonth);
        //今日预约数
        Integer todayOrderNumber=orderDao.todayOrderNumber(today);
        //本周预约数
        Integer thisWeekOrderNumber=orderDao.thisWeekOrderNumber(thisWeekMonday);
        //本月预约数
        Integer thisMonthOrderNumber=orderDao.thisMonthOrderNumber(firstDay4ThisMonth);
        //今日到诊数
        Integer todayVisitsNumber=orderDao.todayVisitsNumber(today);
        //本周到诊数
        Integer thisWeekVisitsNumber=orderDao.thisWeekVisitsNumber(thisWeekMonday);
        //本月到诊数
        Integer thisMonthVisitsNumber=orderDao.thisMonthVisitsNumber(firstDay4ThisMonth);
        //热门套餐（取前4）
        List<Map> hotSetmeal=setMealDao.hotSetmeal();
        Map<String,Object> result = new HashMap<>();
        result.put("reportDate",today);
        result.put("todayNewMember",todayNewMember);
        result.put("totalMember",totalMember);
        result.put("thisWeekNewMember",thisWeekNewMember);
        result.put("thisMonthNewMember",thisMonthNewMember);
        result.put("todayOrderNumber",todayOrderNumber);
        result.put("thisWeekOrderNumber",thisWeekOrderNumber);
        result.put("thisMonthOrderNumber",thisMonthOrderNumber);
        result.put("todayVisitsNumber",todayVisitsNumber);
        result.put("thisWeekVisitsNumber",thisWeekVisitsNumber);
        result.put("thisMonthVisitsNumber",thisMonthVisitsNumber);
        result.put("hotSetmeal",hotSetmeal);
        return result;
    }

    public void setmealcheckgroup(Integer setmeal_id, Integer[] checkgroupIds) {
        if (checkgroupIds!=null&&checkgroupIds.length>0){
            for (Integer checkgroup_id : checkgroupIds) {
                Map<String,Integer> map=new HashMap<>();
                map.put("checkgroup_id",checkgroup_id);
                map.put("setmeal_id",setmeal_id);
                setMealDao.addCheckGroupCheckItem(map);
            }
        }
    }
}
