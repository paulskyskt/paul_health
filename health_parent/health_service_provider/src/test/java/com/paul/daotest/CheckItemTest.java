package com.paul.daotest;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.paul.dao.CheckGroupDao;
import com.paul.dao.CheckItemDao;
import com.paul.pojo.CheckGroup;
import com.paul.pojo.CheckItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-dao.xml")
public class CheckItemTest {

    @Autowired
    private CheckItemDao checkItemDao;

   @Autowired
   private CheckGroupDao checkGroupDao;

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
}
