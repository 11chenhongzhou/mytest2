package com.itheima.health.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.Constant.MessageConstant;
import com.itheima.Constant.RedisMessageConstant;
import com.itheima.dao.MemberDao;
import com.itheima.dao.OrderDao;
import com.itheima.dao.OrderSettingDao;
import com.itheima.mapping.Result;
import com.itheima.pojo.Member;
import com.itheima.pojo.Order;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderService;
import com.itheima.service.OrderSettingService;
import com.itheima.utils.DateUtils;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 15:20 2019/11/6
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderSettingDao orderSettingDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private MemberDao memberDao;
    @Override
    public Result submit(Map map) throws Exception {
        String orderDate = (String) map.get("orderDate");
        Date date = DateUtils.parseString2Date(orderDate);
        OrderSetting orderSetting=orderSettingDao.ifOrderSetting(date);
        if (orderSetting==null){
            return new Result(false,MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }
        int number = orderSetting.getNumber();
        int reservations = orderSetting.getReservations();
        if (reservations>=number){
            return new Result(false,MessageConstant.ORDER_FULL);
        }
        String telephone = (String) map.get("telephone");
        Member member = memberDao.selectIfMember(telephone);
        if (member!=null){
            Integer memberId = member.getId();
            Integer setmealId= Integer.parseInt((String)map.get("setmealId"));
            Order order=new Order(memberId,date,null,null,setmealId);
            List<Order> byCondition = orderDao.findByCondition(order);
            if (byCondition!=null&&byCondition.size()>0){
                return new Result(false,MessageConstant.HAS_ORDERED);
            }
        }else {
            //3.2.不是会员, 需要添加到会员表
            member = new Member();
            member.setName((String) map.get("name"));
            member.setSex((String) map.get("sex"));
            member.setPhoneNumber((String) map.get("telephone"));
            member.setIdCard((String) map.get("idCard"));
            member.setRegTime(new Date());
            memberDao.add(member);
        }
        orderSetting.setReservations(orderSetting.getReservations()+1);
        orderSettingDao.editReservationsByOrderDate(orderSetting);
        Order order = new Order(member.getId(), date, (String) map.get("orderType"), Order.ORDERSTATUS_NO, Integer.parseInt((String) map.get("setmealId")));
        orderDao.add(order);
        return new Result(true,MessageConstant.ORDER_SUCCESS,order);
    }

    @Override
    public Result findById(Integer id) throws Exception {
        Map map=memberDao.findById(id);
        if (map!=null) {
            Date date = (Date) map.get("orderDate");
            String orderDate = DateUtils.parseDate2String(date);
            map.put("orderDate",orderDate);
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);
        }
        return new Result(false,MessageConstant.QUERY_ORDER_FAIL);
    }
}
