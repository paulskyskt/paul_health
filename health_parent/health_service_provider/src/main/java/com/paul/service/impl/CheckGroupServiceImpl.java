package com.paul.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paul.dao.CheckGroupDao;
import com.paul.entity.PageResult;
import com.paul.entity.QueryPageBean;
import com.paul.pojo.CheckGroup;
import com.paul.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

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

    //分页 模糊查询
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //获取分页  模糊查询 参数数据
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        //分页查询
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckGroup> checkGroups =  checkGroupDao.selectByCondition(queryString);

        //封装成PageResult对象
        long total = checkGroups.getTotal();
        List<CheckGroup> result = checkGroups.getResult();

        return new PageResult(total,result);
    }
}
