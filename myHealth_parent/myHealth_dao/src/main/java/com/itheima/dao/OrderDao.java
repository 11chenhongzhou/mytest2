package com.itheima.dao;

import com.itheima.pojo.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.Date;
import java.util.List;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 15:25 2019/11/6
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface OrderDao {
    @Select("select*from t_order where member_id=#{memberId} and orderDate=#{orderDate}")
    List<Order> findByCondition(Order order);
    @Insert("insert into t_order(member_id,orderDate,orderType,orderStatus,setmeal_id)values(#{memberId},#{orderDate},#{orderType},#{orderStatus},#{setmealId})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id",before = false, resultType = Integer.class)
    void add(Order order);
    @Select("SELECT COUNT(*) FROM t_order WHERE orderDate=#{format} and orderStatus='已到诊'")
    Integer todayVisitsNumber(String format);
    @Select("SELECT COUNT(*) FROM t_order WHERE orderDate >=#{thisWeekMonday}")
    Integer thisWeekOrderNumber(String thisWeekMonday);
    //今日预约数
    @Select("SELECT COUNT(*) FROM t_order WHERE orderDate=#{format}")
    Integer todayOrderNumber(String format);
    @Select("SELECT COUNT(*) FROM t_order WHERE orderDate >=#{firstDay4ThisMonth}")
    Integer thisMonthOrderNumber(String firstDay4ThisMonth);
    @Select("SELECT COUNT(*) FROM t_order WHERE orderDate >=#{thisWeekMonday}")
    Integer thisWeekVisitsNumber(String thisWeekMonday);
    @Select("SELECT COUNT(*) FROM t_order WHERE orderDate >=#{firstDay4ThisMonth}")
    Integer thisMonthVisitsNumber(String firstDay4ThisMonth);
}
