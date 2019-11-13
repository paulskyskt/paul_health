package com.paul.security;

import com.alibaba.dubbo.config.annotation.Reference;
import com.paul.pojo.Permission;
import com.paul.pojo.Role;
import com.paul.pojo.User;
import com.paul.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

@Component
public class SpringSecurityUserService implements UserDetailsService {

    @Reference
    private UserService userService;

    //从数据库查询 用户 信息 同时查询出 角色 权限
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //从数据库查询 用户 信息
        User user = userService.findByUserName(username);
        if(user == null) return null;

        List<GrantedAuthority> list = new ArrayList<>();

        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            //角色
            list.add(new SimpleGrantedAuthority(role.getKeyword()));
            //权限
            Set<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                list.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }

        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),list);
    }
}
