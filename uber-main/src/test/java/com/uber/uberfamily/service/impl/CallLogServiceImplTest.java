package com.uber.uberfamily.service.impl;

import com.github.pagehelper.PageInfo;
import com.ps.CustomJUnit4ClassRunner;
import com.uber.uberfamily.service.CallLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service.impl
 * @Description //TODO
 * @Date 16/2/24
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@RunWith(CustomJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class CallLogServiceImplTest {

    @Autowired
    private CallLogService callLogService;


    @Test
    public void test1() {
        PageInfo p = callLogService.getPage(1, 10, null);
        List l = p.getList();

        System.out.println(p);
    }

}