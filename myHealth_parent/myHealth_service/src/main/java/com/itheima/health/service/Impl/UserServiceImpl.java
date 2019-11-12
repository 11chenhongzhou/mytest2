package com.itheima.health.service.Impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.PermissionDao;
import com.itheima.dao.RoleDao;
import com.itheima.dao.UserDao;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;
/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 11:50 2019/11/11
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private UserDao userDao;

    @Override
    public User findUserName(String username) {
        User user = userDao.findUserName(username);
        if (user != null) {
            Set<Role> roleSet= roleDao.findRoleList(user.getId());
            if (roleSet!=null&&roleSet.size()>0){
                for (Role role : roleSet) {
                    Set<Permission> permissionSet=permissionDao.findPermissionList(role.getId());
                    if (permissionSet!=null&&permissionSet.size()>0) {
                        for (Permission permission : permissionSet) {
                            role.setPermissions(permissionSet);
                        }
                    }
                }
                user.setRoles(roleSet);
            }
        }
        return user;
    }
}
