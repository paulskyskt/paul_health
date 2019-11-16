package com.paul.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.paul.dao.MenuDao;
import com.paul.pojo.Aside;
import com.paul.service.MenuService;
import com.paul.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = MenuService.class)
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Aside> findAside() {
        //根据SecurityContextHolder 获取操作者的名字 admin/xiaoming/test
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        //根据name查询出role_id
        //根据role_id 查询出对应菜单id
        //根据菜单id 和parentMenuId 为空查询出一级菜单
        //根据一级菜单id查询出对应子菜单


        return null;
    }
}
