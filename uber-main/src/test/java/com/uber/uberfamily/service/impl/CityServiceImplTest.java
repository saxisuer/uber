package com.uber.uberfamily.service.impl;

import com.github.pagehelper.PageInfo;
import com.ps.CustomJUnit4ClassRunner;
import com.uber.uberfamily.service.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service.impl
 * @Description //TODO
 * @Date 16/3/9
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@RunWith(CustomJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class CityServiceImplTest {


    @Autowired
    private CityService cityService;

    @Test
    public void testLoadCityByAdTemp() throws Exception {
        PageInfo pageInfo = cityService.loadCityByAdTemp(1L);
        System.out.println(pageInfo);
    }
}