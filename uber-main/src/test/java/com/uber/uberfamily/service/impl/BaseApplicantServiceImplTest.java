package com.uber.uberfamily.service.impl;

import com.ps.CustomJUnit4ClassRunner;
import com.uber.uberfamily.service.BaseApplicantService;
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
 * @Date 16/2/18
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@RunWith(CustomJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class BaseApplicantServiceImplTest {


    @Autowired
    private BaseApplicantService baseApplicantService;


    @Test
    public void testGetPage() throws Exception {
        int page = 1;
        int row = 10;
        this.baseApplicantService.getPage(null, page, row);
    }
}