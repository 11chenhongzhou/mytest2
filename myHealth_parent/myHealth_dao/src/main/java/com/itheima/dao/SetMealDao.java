package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.Setmeal;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 19:45 2019/11/3
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface SetMealDao {
    @Select("<script>select*from t_setmeal <if test='value!=null and value.length>0'>where code=#{value} or name=#{value} or helpCode=#{value}</if></script>")
    Page<Setmeal> findPage(String queryString);
    @Insert(" insert into t_setmeal(code,name,helpCode,sex,remark,attention,age,price,img)values(#{code},#{name},#{helpCode},#{sex},#{remark},#{attention},#{age},#{price},#{img})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()",keyProperty ="id",keyColumn = "id",before = false, resultType =Integer.class )
    void addSetmel(Setmeal setmeal);
    @Insert("insert into t_setmeal_checkgroup(setmeal_id,checkgroup_id)values (#{setmeal_id},#{checkgroup_id})")
    void addCheckGroupCheckItem(Map<String, Integer> map);
    @Select("select*from t_setmeal where id=#{id}")
    Setmeal findById(Integer id);
    @Select("select checkgroup_id from t_setmeal_checkgroup where setmeal_id=#{id}")
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    @Select("select*from t_setmeal")
    List<Setmeal> getSetMeal();
    @Select("select*from t_setmeal where id=#{id}")
    @Results(value = {
            @Result(column="id",property ="id",id =true),
            @Result(column="name",property ="name"),
            @Result(column="code",property ="code"),
            @Result(column="helpCode",property ="helpCode"),
            @Result(column="sex",property ="sex"),
            @Result(column="age",property ="age"),
            @Result(column="price",property ="price"),
            @Result(column="remark",property ="remark"),
            @Result(column="attention",property ="attention"),
            @Result(column="img",property ="img"),
            @Result(column="id",property ="checkGroups",javaType = ArrayList.class,many =@Many(select = "com.itheima.dao.SetMealDao.findCheckGroupById")),
    })
    Setmeal findByIdMobile(Integer id);
    @Results(value = {
            @Result(column="id",property ="id",id =true),
            @Result(column="code",property ="code"),
            @Result(column="name",property ="name"),
            @Result(column="helpCode",property ="helpCode"),
            @Result(column="sex",property ="sex"),
            @Result(column="remark",property ="remark"),
            @Result(column="attention",property ="attention"),
            @Result(column="id",property ="checkItems",javaType = ArrayList.class,many =@Many(select = "com.itheima.dao.SetMealDao.findCheckItemById")),
    })
    @Select("select*from t_checkgroup where id in (select checkgroup_id from t_setmeal_checkgroup where setmeal_id=#{id})")
    List<CheckGroup> findCheckGroupById(List<Integer> id);
    @Select("select*from t_checkitem where id in (select checkitem_id from t_checkgroup_checkitem where checkgroup_id=#{id})")
    List<CheckItem> findCheckItemById(List<Integer> id);
    //今日新增会员数
    @Select("select count(*) from t_member where regTime=#{format}")
    Integer todayNewMember(String format);
    //总会员数
    @Select("select count(*) from t_member")
    Integer totalMember();
    //本周新增会员数
    @Select("select count(*) from t_member where regTime>=#{thisWeekMonday}")
    Integer thisWeekNewMember(String thisWeekMonday);
    //本月新增会员数
    @Select("select count(*) from t_member where regTime>=#{firstDay4ThisMonth}")
    Integer thisMonthNewMember(String firstDay4ThisMonth);
    @Select("SELECT ts.name 'name',\n" +
            "COUNT(t.id) setmeal_count,\n" +
            "COUNT(t.id)/(SELECT COUNT(*)FROM t_order)proportion,ts.remark\n" +
            "FROM t_setmeal ts,t_order t WHERE t.`setmeal_id`=ts.id GROUP BY ts.id LIMIT 0,4")
    List<Map> hotSetmeal();
}
