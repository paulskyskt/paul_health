package com.paul.daotest;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.paul.dao.*;
import com.paul.pojo.*;
import com.paul.utils.DateUtils;
import com.qiniu.util.Json;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-dao.xml")
public class CheckItemTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private CheckItemDao checkItemDao;

   @Autowired
   private CheckGroupDao checkGroupDao;

   @Autowired
   private SetmealDao setmealDao;

   @Autowired
   private MemberDao memberDao;

   @Autowired
   private SysLogDao sysLogDao;

    @Autowired
    private MenuDao menuDao;

    @Test
    public void test21(){

        List<Menu> secondMenus = menuDao.findSecondMenuByFirstMenu_Id(1);
        Object json = JSON.toJSON(secondMenus);
        System.out.println(json);
    }

    @Test
    public void test20(){
        //根据name查询出role_id
        Integer role_id = menuDao.findRole_idByUsername("test");
        //根据role_id 查询出对应菜单menu_id
        List<Integer> menu_ids = menuDao.findMenu_idsByRole_id(role_id);

        //获取首位
        Integer f = menu_ids.get(0);
        //获取末位
        Integer l = menu_ids.get(menu_ids.size() - 1);

        //根据菜单id范围 和parentMenuId 为空查询出一级菜单
        List<Menu> firstMenuList = menuDao.findFirstMenuByRole_id(f,l);


        //根据一级菜单parentMenuId查询出对应子菜单
        //根据一级菜单parentMenuId查询出对应子菜单
        List<List<Menu>> secondMenuList =  new ArrayList<>();
        for (Menu menu : firstMenuList) {
            Integer parentMenuId = menu.getId();
            List<Menu> secondMenus = menuDao.findSecondMenuByFirstMenu_Id(parentMenuId);
            secondMenuList.add(secondMenus);
        }

        //
        for (int i = 0; i < secondMenuList.size(); i++) {
            List<Menu> menuList = secondMenuList.get(i);
            Menu menu = firstMenuList.get(i);
            menu.setChildren(menuList);
        }






        Object json = JSON.toJSON(firstMenuList);
        System.out.println(json);



    }

    @Test
    public void test19(){
        String date  = "2019-1-2";

        Date date1 = null;
        try {
            date1 = DateUtils.parseString2Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sysLogDao.deleteByTime(date1);
    }

    @Test
    public void test18(){
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(2L);
        sysLog.setIp("0.0.01");
        sysLog.setMethod("[类名]"+"class"+"[方法名]"+"method");
        sysLog.setVisitTime(new Date());
        sysLog.setUsername("admin");
        sysLog.setUrl("/elementui/index.js");
        sysLogDao.save(sysLog);
    }

    @Test
    public void test17(){
        Integer count = memberDao.findCountByAddress("北京");
        System.out.println(count);
    }


    @Test
    public void test16(){
        List<String> list = memberDao.findAllAddress();
        for (String s : list) {
            System.out.println(s);
        }
    }

    @Test
    public void test15(){
        List<User> users = userDao.findUsersByRoleId(1);
        for (User user : users) {
            String username = user.getUsername();
            System.out.println(username);
        }
    }


    @Test
    public void test14(){
        Permission permissionById = roleDao.findPermissionById(1);
        System.out.println(permissionById.getName());
      /*  List<Integer> list = roleDao.find_M_permission_role(1);
        for (Integer integer : list) {
            System.out.println(integer);
        }*/
    }

    @Test
    public void test13(){
        Page<Role> page = roleDao.findPage("管理");
        List<Role> result = page.getResult();
        for (Role role : result) {
            System.out.println(role.getName());
        }
    }

   @Test
   public void test12(){
       User admin = userDao.findByUserName("admin");
       System.out.println(admin.getId());
   }

    @Test
    public void test11(){
        Set<Permission> permissions = permissionDao.findByRoleId(2);
        for (Permission permission : permissions) {
            System.out.println(permission.getId());
        }
    }


    @Test
    public void test10(){
        Set<Role> roles = roleDao.findByUserId(3);
        for (Role role : roles) {
            System.out.println(role);
        }
    }

    @Test
    public void test9(){
        Setmeal setmeal = setmealDao.findById(12);
        List<CheckGroup> checkGroups = setmeal.getCheckGroups();
        for (CheckGroup checkGroup : checkGroups) {
            System.out.println(checkGroup);
        }
    }

    @Test
    public void test8(){
        List<CheckGroup> checkGroupById = checkGroupDao.findCheckGroupById(12);
        for (CheckGroup checkGroup : checkGroupById) {
            System.out.println(checkGroup);
        }
    }


    @Test
    public void test7(){
        List<Setmeal> all = setmealDao.findAll();
        for (Setmeal setmeal : all) {
            System.out.println(setmeal);
        }
    }

    @Test
    public void test6(){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("checkgroupId",5);
        map.put("setmealId",15);
        setmealDao.setSetmealAndCheckgroup(map);
    }

   @Test
   public void test5(){
       Setmeal setmeal = new Setmeal();
        setmeal.setAge("20-29");
       setmealDao.add(setmeal);
       Integer id = setmeal.getId();
       System.out.println(id);
   }

    @Test
    public void test1(){
        Page<CheckItem> items = checkItemDao.selectByCondition("力");
        List<CheckItem> result = items.getResult();
        for (CheckItem checkItem : result) {
            System.out.println(checkItem);
        }
    }
    @Test
    public void test2(){
        List<Integer> checkItemIdsByCheckGroupId = checkGroupDao.findCheckItemIdsByCheckGroupId(10);
        for (Integer integer : checkItemIdsByCheckGroupId) {
            System.out.println(integer);
        }
    }

    @Test
    public void test3(){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("checkGroupId",18);
        map.put("checkItemId",33);
        checkGroupDao.updateCheckItemForCheckGroup(map);
    }

    @Test
    public void test4(){
        Page<Setmeal> page = setmealDao.findPage("男");
        List<Setmeal> result = page.getResult();
        for (Setmeal setmeal : result) {
            System.out.println(setmeal);
        }
    }
}
