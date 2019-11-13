package com.paul.service.impl;

import com.paul.dao.PermissionDao;
import com.paul.dao.RoleDao;
import com.paul.dao.UserDao;
import com.paul.pojo.Permission;
import com.paul.pojo.Role;
import com.paul.pojo.User;
import com.paul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public User findByUserName(String username) {
        //查询用户
        User user = userDao.findByUserName(username);
        if(user == null) return null;

        Integer id = user.getId();
        //查询用户角色
        Set<Role> roles =  roleDao.findByUserId(id);
        if(roles != null && roles.size() > 0){
            for (Role role : roles) {
                Integer roleId = role.getId();
                //角色的权限
                Set<Permission> permissions = permissionDao.findByRoleId(roleId);
                if(permissions!=null && permissions.size()>0){
                    role.setPermissions(permissions);
                }
            }
        }
        user.setRoles(roles);

        return user;
    }
}
