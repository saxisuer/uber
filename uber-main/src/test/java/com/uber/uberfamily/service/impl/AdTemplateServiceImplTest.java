package com.uber.uberfamily.service.impl;

import com.ps.CustomJUnit4ClassRunner;
import com.uber.uberfamily.model.AdTemplate;
import com.uber.uberfamily.service.AdTemplateService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service.impl
 * @Description //TODO
 * @Date 16/3/7
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@RunWith(CustomJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class AdTemplateServiceImplTest {

    @Autowired
    private AdTemplateService adTemplateService;

    @Test
    public void testGetById() throws Exception {
        AdTemplate adTemplate = adTemplateService.getById(18L);
        System.out.println(adTemplate.toString());
    }


    @Test
    public void test() {
        List<Long> a = new ArrayList<Long>();
        String s = StringUtils.join(a, ",");
        System.out.println(s);
    }


    @Test
    public void testIns() {
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (int i = 1; i <= 3; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("tempId", 2);
            map.put("fileId", i);
            mapList.add(map);
        }
        this.adTemplateService.createRel(mapList);
    }
}