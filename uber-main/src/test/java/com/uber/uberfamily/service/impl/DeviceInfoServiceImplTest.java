package com.uber.uberfamily.service.impl;

import com.github.pagehelper.PageInfo;
import com.ps.CustomJUnit4ClassRunner;
import com.uber.uberfamily.model.DeviceInfo;
import com.uber.uberfamily.service.DeviceInfoService;
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
 * @Date 16/2/22
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@RunWith(CustomJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class DeviceInfoServiceImplTest {


    @Autowired
    private DeviceInfoService deviceInfoService;


    @Test
    public void test1() {
        PageInfo<DeviceInfo> pageInfo = deviceInfoService.getPage(null, 1, 10);
        System.out.println(pageInfo);
    }


    @Test
    public void test2() {
        List l = deviceInfoService.statistic(null);
        System.out.println(l);
    }


    @Test
    public void test3() {
        List l = deviceInfoService.getListByType("city", "32,23,23");
        System.out.println(l);
    }
}