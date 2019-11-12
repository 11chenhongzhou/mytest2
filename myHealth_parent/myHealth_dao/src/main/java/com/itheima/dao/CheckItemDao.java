package com.itheima.dao;
import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 持久层Dao接口
 */
public interface CheckItemDao {
    @Insert(" insert into t_checkitem(code,name,sex,age,price,type,remark,attention)" +
            "values(#{code},#{name},#{sex},#{age},#{price},#{type},#{remark},#{attention})")
    void addCheckItem(CheckItem checkItem);
    @Select("<script>SELECT * FROM t_checkitem<if test='value!=null and value.length>0'>where code=#{value} or name=#{value}</if></script>")
    Page<CheckGroup> selectIf(String queryString);
    @Select("SELECT *FROM t_checkitem WHERE id=#{id}")
    CheckItem findById(int id);
    @Select("select count(*) from t_checkgroup_checkitem where checkitem_id=#{id}")
    int selectIfAssociate(int id);
    @Delete("delete from t_checkitem where id=#{id}")
    void delete(int id);
    @Update("<script>update t_checkitem" +
            "<set>" +
            "<if test='code!=null'>" +
            "code=#{code}," +
            "</if>" +
            "<if test='name!=null'>" +
            "name=#{name}," +
            "</if>" +
            "<if test='sex!=null'>" +
            "sex=#{sex}," +
            "</if>" +
            "<if test='age!=null'>" +
            "age=#{age}," +
            "</if>" +
            "<if test='price!=null'>" +
            "price=#{price}," +
            "</if>" +
            "<if test='type!=null'>" +
            "type=#{type}," +
            "</if>" +
            "<if test='remark!=null'>" +
            "remark=#{remark}," +
            "</if>" +
            "<if test='attention!=null'>" +
            "attention=#{attention}," +
            "</if></set>" +
            "<where>" +
            "id=#{id}" +
            "</where>" +
            "</script>")
    void edit(CheckItem checkItem);
    @Select("select* from t_checkitem")
    List<CheckItem> findAll();
    @Select("select checkitem_id from t_checkgroup_checkitem where checkgroup_id=#{checkgroup_id}")
    List<Integer> findCheckItemIdsByCheckGroupId(Integer checkgroup_id);
}
