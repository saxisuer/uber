package com.ps;

import com.uber.uberfamily.model.BaseCard;
import com.uber.uberfamily.service.BaseCardService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * @Project uber
 * @Package com.ps
 * @Description //TODO
 * @Date 16/1/20
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@RunWith(CustomJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class MybatisTest {

    private static final Logger logger = Logger.getLogger(MybatisTest.class);

    @Autowired
    private BaseCardService baseCardService;


    @Test
    public void test1() {
        BaseCard baseCard = baseCardService.getById(57L);
        logger.info(baseCard);
    }
}
