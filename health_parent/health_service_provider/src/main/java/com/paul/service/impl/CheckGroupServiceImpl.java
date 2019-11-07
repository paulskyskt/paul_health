package com.paul.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.paul.dao.CheckGroupDao;
import com.paul.pojo.CheckGroup;
import com.paul.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    //新增检查组，同时检查项和检查组关联
    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {

        //新增检查组，
        checkGroupDao.add(checkGroup);
        //设置检查组和检查项的多对多的关联关系
        Integer checkGroupId = checkGroup.getId();
        if(checkitemIds !=null && checkitemIds.length>0){
            for (Integer checkitemId : checkitemIds) {
                HashMap<String, Integer> map = new HashMap<>();
                map.put("checkGroupId",checkGroupId);
                map.put("checkitemId",checkitemId);
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }

    }
}
