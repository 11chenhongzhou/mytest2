package com.itheima.dao;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.pojo.Menu;
import org.apache.ibatis.annotations.Select;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 16:19 2019/11/11
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface DemandMuEnDao {
    @Select("select*from t_menu tm,t_role_menu trm where trm.menu_id=tm.id and trm.role_id=#{id} and tm.parentMenuId IS NULL")
    LinkedHashSet<Menu> findMemu(Integer id);
    @Select("select*from t_menu tm,t_role_menu trm where trm.menu_id=tm.id and tm.parentMenuId=#{id}")
    List<Menu> findChildren(Integer id);
}
