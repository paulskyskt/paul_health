package com.paul.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.paul.dao.MenuDao;
import com.paul.pojo.Aside;
import com.paul.pojo.Children;
import com.paul.pojo.Menu;
import com.paul.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service(interfaceClass = MenuService.class)
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> findAside(String username) {
        //根据name查询出role_id
        Integer role_id = menuDao.findRole_idByUsername(username);
        //根据role_id 查询出对应菜单menu_id
        List<Integer> menu_ids = menuDao.findMenu_idsByRole_id(role_id);

        //获取首位
        Integer f = menu_ids.get(0);
        //获取末位
        Integer l = menu_ids.get(menu_ids.size() - 1);

        //根据菜单id范围 和parentMenuId 为空查询出一级菜单
        List<Menu> firstMenuList = menuDao.findFirstMenuByRole_id(f,l);


        //根据一级菜单parentMenuId查询出对应子菜单
        List<List<Menu>> secondMenuList =  new ArrayList<>();
        for (Menu menu : firstMenuList) {
            Integer parentMenuId = menu.getId();
            List<Menu> secondMenus = menuDao.findSecondMenuByFirstMenu_Id(parentMenuId);
            secondMenuList.add(secondMenus);
        }

        for (int i = 0; i < secondMenuList.size(); i++) {
            List<Menu> menuList = secondMenuList.get(i);
            Menu menu = firstMenuList.get(i);
            menu.setChildren(menuList);
        }


        return firstMenuList;
    }
}
