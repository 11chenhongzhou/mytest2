package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 23:07 2019/11/2
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface CheckGroupDao {
    @Insert(" insert into t_checkgroup(code,name,helpCode,sex,remark,attention)values(#{code},#{name},#{helpCode},#{sex},#{remark},#{attention})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()",keyProperty ="id",keyColumn = "id",before = false, resultType =Integer.class )
    void addCheckGroup(CheckGroup checkGroup);
    @Insert("insert into t_checkgroup_checkitem(checkgroup_id,checkitem_id)values (#{checkgroup_id},#{checkitem_id})")
    void addCheckGroupCheckItem(Map map);
    @Select("Select *from t_checkgroup where id=#{id}")
    CheckGroup findById(Integer id);
    @Select("<script>select*from t_checkgroup <if test='value!=null and value.length>0'>where code=#{value} or name=#{value} or helpCode=#{value}</if></script>")
    Page<CheckGroup> findPage(String queryString);
    @Update("<script>update t_checkgroup" +
            "<set>" +
            "<if test='code!=null'>code=#{code}," +
            "</if>" +
            "<if test='name!=null'>name=#{name}," +
            "</if><if test='helpCode!=null'>helpCode=#{helpCode}," +
            "</if>" +
            "<if test='sex!=null'>sex=#{sex}," +
            "</if>" +
            "<if test='remark!=null'>remark=#{remark}," +
            "</if>" +
            "<if test='attention!=null'>attention=#{attention}," +
            "</if>" +
            "</set>" +
            "<where>" +
            "id=#{id}" +
            "</where>" +
            "</script>")
    void updateCheckGroup(CheckGroup checkGroup);
    @Delete("delete from t_checkgroup_checkitem where checkgroup_id=#{id}")
    void delete(Integer id);
    @Select("select*from t_checkgroup ")
    List<CheckGroup> findAll();
}
