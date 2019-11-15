package com.paul.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paul.dao.SysLogDao;
import com.paul.entity.PageResult;
import com.paul.entity.QueryPageBean;
import com.paul.pojo.Setmeal;
import com.paul.pojo.SysLog;
import com.paul.service.ISysLogService;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service(interfaceClass = ISysLogService.class)
@Transactional
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //获取分页 模糊查询信息
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        //
        //分页
        PageHelper.startPage(currentPage, pageSize);
        Page<Setmeal> setmeals = sysLogDao.findPage(queryString);

        List<Setmeal> list = setmeals.getResult();
        long total = setmeals.getTotal();

        return new PageResult(total, list);
    }

    @Override
    public void deleteById(Integer id) {
        sysLogDao.deleteById(id);
    }

    @Override
    public void deleteByTime(Date date1) {
        sysLogDao.deleteByTime(date1);
    }
}
