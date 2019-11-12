package com.itheima.dao;

import com.itheima.pojo.Role;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 14:43 2019/11/11
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface RoleDao {
    @Select("select*from t_role tr,t_user_role tur where tr.id=tur.role_id and tur.user_id=#{id}")
    Set<Role> findRoleList(Integer id);
}
