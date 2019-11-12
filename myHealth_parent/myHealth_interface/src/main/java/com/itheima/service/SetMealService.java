package com.itheima.service;

import com.itheima.mapping.PageResult;
import com.itheima.mapping.QueryPageBean;
import com.itheima.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 19:43 2019/11/3
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface SetMealService {
    PageResult findPage(QueryPageBean queryPageBean);
    void add(Setmeal setmeal, Integer[] checkgroupIds);
    Setmeal findById(Integer id);
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    List<Setmeal> getSetMeal();
    Setmeal findByIdMobile(Integer id);
    Map getBusinessReportData() throws Exception;
}
