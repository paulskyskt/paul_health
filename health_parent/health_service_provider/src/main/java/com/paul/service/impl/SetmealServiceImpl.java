package com.paul.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paul.constant.RedisConstant;
import com.paul.dao.SetmealDao;
import com.paul.entity.PageResult;
import com.paul.entity.QueryPageBean;
import com.paul.pojo.Setmeal;
import com.paul.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;


import java.util.HashMap;
import java.util.List;
/**
 * @author Think
 */
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;

    @Autowired
    private JedisPool jedisPool;


    @Override

    public PageResult findPage(QueryPageBean queryPageBean) {
        //获取分页 模糊查询信息
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        //分页
        PageHelper.startPage(currentPage, pageSize);
        Page<Setmeal> setmeals = setmealDao.findPage(queryString);

        List<Setmeal> list = setmeals.getResult();
        long total = setmeals.getTotal();

        return new PageResult(total, list);
    }

    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealDao.add(setmeal);

        if ( checkgroupIds != null && checkgroupIds.length > 0 ) {
            for (Integer checkgroupId : checkgroupIds) {
                HashMap<String, Integer> map = new HashMap<>();
                map.put("checkgroupId", checkgroupId);
                map.put("setmealId", setmeal.getId());
                setmealDao.setSetmealAndCheckgroup(map);
            }
        }

        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());

    }

    @Override
    public List<Setmeal> findAll() {
        return setmealDao.findAll();
    }

    @Override
    public Setmeal findById(Integer id) {
        return setmealDao.findById(id);
    }
}
