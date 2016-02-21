package com.uber.uberfamily.service.impl;

import com.ps.CustomJUnit4ClassRunner;
import com.uber.uberfamily.service.CallCardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service.impl
 * @Description //TODO
 * @Date 16/2/21
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@RunWith(CustomJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class CallCardServiceImplTest {


    @Autowired
    private CallCardService callCardService;


    @Test
    public void test1() {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("cardNoLogical", "1000000020");
        paramMap.put("operatorAcc", "admin");
        Map a = this.callCardService.getBaseDao().sp_setExceptionLock(paramMap);
        System.out.println(a);
    }
}