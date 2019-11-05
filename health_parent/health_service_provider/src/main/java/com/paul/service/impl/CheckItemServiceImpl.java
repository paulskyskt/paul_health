package com.paul.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paul.dao.CheckItemDao;
import com.paul.entity.PageResult;
import com.paul.entity.QueryPageBean;
import com.paul.pojo.CheckItem;
import com.paul.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检查项目服务
 */
@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {

        //获取分页信息
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        //分页
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckItem> checkItems = checkItemDao.selectByCondition(queryString);

        //获取总记录数和数据封装成对象
        long total = checkItems.getTotal();
        List<CheckItem> rows = checkItems.getResult();

        return new PageResult(total,rows);
    }


}
