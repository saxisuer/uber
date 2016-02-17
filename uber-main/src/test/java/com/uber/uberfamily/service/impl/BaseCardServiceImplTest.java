package com.uber.uberfamily.service.impl;

import com.ps.CustomJUnit4ClassRunner;
import com.uber.uberfamily.model.BaseCard;
import com.uber.uberfamily.service.BaseCardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service.impl
 * @Description //TODO
 * @Date 16/2/16
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@RunWith(CustomJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class BaseCardServiceImplTest {


    @Autowired
    private BaseCardService baseCardService;


    @Test
    @Rollback
    public void testSaveExcelData() throws Exception {
        List<BaseCard> list = new ArrayList<BaseCard>();
        for (int i = 0; i < 10; i++) {
            BaseCard baseCard = new BaseCard();
            baseCard.setCardNoLogical("1111111111" + i);
            baseCard.setCardNoPhysical("2222222222" + i);
            baseCard.setCreateByWho("admin123123123");
            baseCard.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
            baseCard.setCreateTime(new Date());
            list.add(baseCard);
        }
        this.baseCardService.saveExcelData(list);
    }
}