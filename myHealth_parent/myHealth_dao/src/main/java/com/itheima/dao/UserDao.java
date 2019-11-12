package com.itheima.dao;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Select;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 14:42 2019/11/11
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface UserDao {
    @Select("select*from t_user where username=#{username}")
    User findUserName(String username);
}
