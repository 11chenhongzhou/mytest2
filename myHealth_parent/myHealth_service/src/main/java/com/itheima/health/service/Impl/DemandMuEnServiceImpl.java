package com.itheima.health.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.DemandMuEnDao;
import com.itheima.dao.RoleDao;
import com.itheima.dao.UserDao;
import com.itheima.pojo.Menu;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import com.itheima.service.DemandMuEnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 15:41 2019/11/11
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@Service(interfaceClass =DemandMuEnService.class)
@Transactional
public class DemandMuEnServiceImpl implements DemandMuEnService {
    @Autowired
    private DemandMuEnDao demandMuEnDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Override
    public LinkedHashSet<Menu> findAllMuEn(String username) {
        User user = userDao.findUserName(username);
        LinkedHashSet<Menu> menuList=new LinkedHashSet<>();
        if (user!=null){
            Set<Role> roleSet= roleDao.findRoleList(user.getId());
            if (roleSet!=null&&roleSet.size()>0){
                for (Role role : roleSet) {
                    menuList =demandMuEnDao.findMemu(role.getId());
                    if (menuList!=null && menuList.size()>0) {
                        for (Menu menu : menuList) {
                            List<Menu> children=demandMuEnDao.findChildren(menu.getId());
                            menu.setChildren(children);
                            menu.setRoles(roleSet);
                        }
                    }
                }
            }
        }
        return menuList;
    }
}
