package com.uber.uberfamily.service.impl;

import com.uber.uberfamily.dao.PermissionDao;
import com.uber.uberfamily.framework.BaseServiceImpl;
import com.uber.uberfamily.framework.MyBatisService;
import com.uber.uberfamily.model.Permission;
import com.uber.uberfamily.service.PermissionService;

import javax.annotation.Resource;
import java.util.*;


/**
 * @Project uber
 * @Package com.uber.uberfamily.service.impl
 * @Description //TODO
 * @Date 16/1/20
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@MyBatisService("permissionService")
public class PermissionServiceImpl extends BaseServiceImpl<Permission, Long, PermissionDao> implements PermissionService {

    @Override
    @Resource(name = "permissionDao")
    public void setBaseDao(PermissionDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public List<Permission> getModuleGroups() {
        List<Permission> l = this.getBaseDao().getModuleGroups();
        List<Permission> resultList = new ArrayList<Permission>();
        Map<String, String> m = new HashMap<String, String>();
        buildResultList(l, resultList, m);
        return resultList;
    }

    @Override
    public List<Permission> getAllPermission() {
        List<Permission> l = this.getBaseDao().getList(null);
        List<Permission> resultList = new ArrayList<Permission>();
        Map<String, String> m = new HashMap<String, String>();
        buildResultList(l, resultList, m);
        return resultList;
    }

    @Override
    public Set<Permission> getChildren(Long permissionId) {
        return this.getBaseDao().getChildren(permissionId);
    }

    @Override
    public List<Permission> getPermission(Map<String, Object> paramMap) {
        return this.getBaseDao().getPermission(paramMap);
    }


    private void buildResultList(List<Permission> l, List<Permission> resultList, Map<String, String> m) {
        String permissionCode;
        for (Permission aL : l) {
            permissionCode = m.get(aL.getCode());
            if (permissionCode == null) {
                resultList.add(aL);
                m.put(aL.getCode(), aL.getCode());
            }
        }
    }
}
