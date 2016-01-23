package com.uber.uberfamily.service.impl;

import com.ps.CustomJUnit4ClassRunner;
import com.uber.uberfamily.model.Role;
import com.uber.uberfamily.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service.impl
 * @Description //TODO
 * @Date 16/1/22
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@RunWith(CustomJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class RoleServiceImplTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void testGetRoleById() throws Exception {
        Role role = roleService.getRoleById(1L);
        System.out.println(role.getName());
        System.out.println("-----------lazy");
        System.out.println(role.getPermissionSet());
    }


    @Test
    public void testGetRoleSetByUserId() {
        Set<Role> roleSet = roleService.getRoleSetByUserId(1L);
        for (Role r : roleSet) {
            System.out.println(r);
        }
    }

}