package com.paul.daotest;

import com.github.pagehelper.Page;
import com.paul.dao.*;
import com.paul.pojo.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

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

    @Test
    public void test21(){
    }

    @Test
    public void test20(){
    }

    @Test
    public void test19(){
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
