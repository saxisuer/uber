package com.uber.uberfamily.service.impl;

import com.github.pagehelper.PageInfo;
import com.ps.CustomJUnit4ClassRunner;
import com.uber.uberfamily.model.Role;
import com.uber.uberfamily.service.RoleService;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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


    @Test
    public void testRoleMap() {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", 0);
        List l = roleService.getRoleMap(param);
        System.out.println(l);
    }

    @Test
    public void testPage() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Role role = new Role();
        role.setName("a");
        Map map = BeanUtils.describe(role);
        PageInfo<Role> pageInfo = roleService.getPage(map, 1, 10);
        System.out.println(pageInfo.getTotal());
    }

}