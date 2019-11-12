package com.itheima.dao;
import com.itheima.pojo.OrderSetting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 11:27 2019/11/4
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface OrderSettingDao {
    @Select("select count(*) from t_ordersetting where orderDate=#{orderDate}")
    int selectIfDateExist(Date orderDate);
    @Update("update t_ordersetting set reservations=#{reservations},number=#{number} where orderDate=#{orderDate}")
    void updateOrderSetting(OrderSetting orderSetting);
    @Insert("insert into t_ordersetting (orderDate,reservations,number)values(#{orderDate},#{reservations},#{number})")
    void addOrderSetting(OrderSetting orderSetting);
    @Select("select *from t_ordersetting where orderDate like #{currentYearAndMonth}")
    List<OrderSetting> getOrderSettingByMonth(String currentYearAndMonth);
    @Select("select*from t_ordersetting where orderDate=#{orderDate}")
    OrderSetting ifOrderSetting(Date orderDate);
    @Update("update t_ordersetting set reservations=#{reservations} where orderDate=#{orderDate}")
    void editReservationsByOrderDate(OrderSetting orderSetting);

}
