package com.itheima.dao;

import com.itheima.pojo.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.Map;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 17:20 2019/11/6
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface MemberDao {
    @Select("select*from t_member where phoneNumber=#{telephone}")
    Member selectIfMember(String telephone);
    @Insert("insert into t_member(name,sex,idCard,phoneNumber,regTime)values(#{name},#{sex},#{idCard},#{phoneNumber},#{regTime})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id",before = false, resultType = Integer.class)
    void add(Member member);
    @Select("SELECT m.name member,s.name setmeal,o.orderDate orderDate,o.orderType orderType FROM t_order o,t_member m,t_setmeal s WHERE o.id=#{id} AND m.`id`=o.`member_id` AND o.`setmeal_id`=s.id")
    Map findById(Integer id);
}
