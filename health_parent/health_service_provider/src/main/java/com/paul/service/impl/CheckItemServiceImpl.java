package com.paul.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.paul.dao.CheckItemDao;
import com.paul.pojo.CheckItem;
import com.paul.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
}
