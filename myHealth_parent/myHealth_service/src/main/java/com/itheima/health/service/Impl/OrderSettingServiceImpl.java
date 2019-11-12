package com.itheima.health.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.OrderSettingDao;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 11:16 2019/11/4
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    public void upload(List<OrderSetting> orderSettingsList) {
        for (OrderSetting orderSetting : orderSettingsList) {
            fun(orderSetting);
        }
    }
    @Override
    public List<OrderSetting> getOrderSettingByMonth(String currentYearAndMonth) {

        return orderSettingDao.getOrderSettingByMonth(currentYearAndMonth);
    }
    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        fun(orderSetting);
    }
    public void fun(OrderSetting orderSetting) {
        long count = orderSettingDao.selectIfDateExist(orderSetting.getOrderDate());
        if (count > 0) {
            orderSettingDao.updateOrderSetting(orderSetting);
        } else {
            orderSettingDao.addOrderSetting(orderSetting);
        }
    }
}
