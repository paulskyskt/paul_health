package com.paul.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paul.dao.RoleDao;
import com.paul.entity.PageResult;
import com.paul.entity.QueryPageBean;
import com.paul.pojo.Permission;
import com.paul.pojo.Role;
import com.paul.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Think
 */
@Service(interfaceClass = RoleService.class)
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //获取参数
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        //分页
        PageHelper.startPage(currentPage,pageSize);
        Page<Role> rolePage =  roleDao.findPage(queryString);

        //
        long total = rolePage.getTotal();
        List<Role> result = rolePage.getResult();
        return new PageResult(total,result);
    }

    @Override
    public List<Permission> findPermissionByRoleId(Integer id) {

        //查询中间表
        List<Integer> list =roleDao.find_M_permission_role(id);

        //集合装
        List<Permission> reuslt = new ArrayList<>();

        if(list.size()>0 && list!=null){
            for (Integer integer : list) {
                Permission permission = roleDao.findPermissionById(integer);
                reuslt.add(permission);
            }
        }

        return reuslt;
    }
}
