package com.uber.uberfamily.service.impl;

import com.ps.CustomJUnit4ClassRunner;
import com.uber.uberfamily.service.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service.impl
 * @Description //TODO
 * @Date 16/1/20
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@RunWith(CustomJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class PermissionServiceImplTest {


    @Autowired
    public PermissionService permissionService;

    @Test
    public void getModuleGroups() {
        System.out.println(permissionService.getModuleGroups());
    }

    @Test
    public void getAllPermission() {
        System.out.println(permissionService.getAllPermission());
    }


    @Test
    public void getChildren() {
        System.out.println(permissionService.getChildren(10L));
    }
}