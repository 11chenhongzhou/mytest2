package com.itheima.dao;

import com.itheima.pojo.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 14:50 2019/11/11
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface PermissionDao {
    @Select("select*from t_role_permission trp,t_permission tp where trp.permission_id=tp.id and trp.role_id=#{id}")
    Set<Permission> findPermissionList(Integer id);
}
