package com.lcl.datareplenish.service.impl;

import com.lcl.datareplenish.dao.firstdatasource.TestMapper;
import com.lcl.datareplenish.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: TestServiceImpl
 * @date 2020/9/6 12:41 上午
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;
    @Override
    public String testAutowired(String msg) {
        return testMapper.getTestDataFromECS();
    }


}
