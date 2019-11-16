package com.paul.dao;

import com.paul.pojo.Menu;

import java.util.List;

public interface MenuDao {
    Integer findRole_idByUsername(String username);

    List<Menu> findFirstMenuByRole_id(Integer first,Integer last);

    List<Integer> findMenu_idsByRole_id(Integer role_id);

    List<Menu> findSecondMenuByFirstMenu_Id(Integer firstMenu_id);
}




