package com.itheima.service;
import com.itheima.pojo.OrderSetting;

import java.util.Date;
import java.util.List;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 11:16 2019/11/4
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface OrderSettingService {
    void upload(List<OrderSetting> orderSettingsList);
    List<OrderSetting> getOrderSettingByMonth(String currentYearAndMonth);
    void editNumberByDate(OrderSetting orderSetting);
}
