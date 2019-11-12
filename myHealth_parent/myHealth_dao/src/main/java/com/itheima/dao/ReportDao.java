package com.itheima.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 21:01 2019/11/11
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface ReportDao {
    @Select("select count(*)from t_member where regTime<=#{months}")
    Integer getMemberReport(String months);
    @Select("SELECT s.name,COUNT(o.id) value FROM t_order o,t_setmeal s WHERE o.`setmeal_id`=s.`id` GROUP BY s.`name`")
    List<Map> getSetmealReport();
}
