package com.itheima.security;
import	java.util.ArrayList;
import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Set;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 11:48 2019/11/11
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@Component
public class SpringSecurityUserService implements UserDetailsService {
    @Reference
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userService.findUserName(username);
        if (user==null) {
            return null;
        }
        List<GrantedAuthority>list=new ArrayList<> ();
        Set<Role> roles = user.getRoles();
        if (roles!=null&&roles.size()>0){
            for (Role role : roles) {
                list.add(new SimpleGrantedAuthority(role.getKeyword()));
                Set<Permission> permissions = role.getPermissions();
                if (permissions!=null&&permissions.size()>0){
                    for (Permission permission : permissions) {
                        list.add(new SimpleGrantedAuthority(permission.getKeyword()));
                    }
                }
            }
        }
        org.springframework.security.core.userdetails.User userDetails=new org.springframework.security.core.userdetails.User(username,user.getPassword(),list);
        return userDetails;
    }
}
