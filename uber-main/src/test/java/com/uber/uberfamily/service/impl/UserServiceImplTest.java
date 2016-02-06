package com.uber.uberfamily.service.impl;

import com.github.pagehelper.PageInfo;
import com.ps.CustomJUnit4ClassRunner;
import com.uber.uberfamily.model.User;
import com.uber.uberfamily.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service.impl
 * @Description //TODO
 * @Date 16/1/21
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@RunWith(CustomJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGetUserByName() throws Exception {
        System.out.println(userService.getUserByName("admin"));
    }


    @Test
    public void testLoadPage() {
        PageInfo<User> page = userService.getPage(null, 1, 10);
        page.getList();
        page.getTotal();
    }
}