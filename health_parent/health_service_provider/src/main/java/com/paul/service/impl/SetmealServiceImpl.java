package com.paul.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paul.dao.SetmealDao;
import com.paul.entity.PageResult;
import com.paul.entity.QueryPageBean;
import com.paul.pojo.Setmeal;
import com.paul.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //获取分页 模糊查询信息
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        //分页
        PageHelper.startPage(currentPage,pageSize);
        Page<Setmeal> setmeals = setmealDao.findPage(queryString);

        List<Setmeal> list = setmeals.getResult();
        long total = setmeals.getTotal();

        return new PageResult(total,list);
    }
}
