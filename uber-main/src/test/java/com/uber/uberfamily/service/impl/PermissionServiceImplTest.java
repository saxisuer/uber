package com.uber.uberfamily.service.impl;

import com.ps.CustomJUnit4ClassRunner;
import com.uber.uberfamily.framework.util.JsonMapper;
import com.uber.uberfamily.model.Permission;
import com.uber.uberfamily.service.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

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


    @Test
    public void getPermissionTest() {
        List l = permissionService.getPermission(null);
        System.out.println(l);
    }


    @Test
    public void testTree() {
        List<Permission> permissionList = permissionService.getList(null);
        List<Permission> first = new ArrayList<>();
        List<Permission> other = new ArrayList<>();
        for (Permission p : permissionList) {
            if (null != p.getPid()) {
                other.add(p);
            } else {
                first.add(p);
            }
        }
        Iterator<Permission> it = first.iterator();
        while (it.hasNext()) {
            build(it.next(), other);
        }

        System.out.println(JsonMapper.nonDefaultMapper().toJson(first));
    }

    public void build(Permission p, List<Permission> list) {
        Iterator<Permission> it = list.iterator();
        while (it.hasNext()) {
            Permission permission = it.next();
            if (Objects.equals(permission.getPid(), p.getId())) {
                p.getPermissionSet().add(permission);
                it.remove();
                build(permission, list);
            }
        }
    }


    @Test
    public void testPermissionMapList() {
        List l = permissionService.getPermissionMap(null);

        System.out.println(l);
    }
}