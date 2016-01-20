package com.uber.uberfamily.service;

import com.uber.uberfamily.dao.PermissionDao;
import com.uber.uberfamily.framework.BaseService;
import com.uber.uberfamily.model.Permission;

import java.util.List;
import java.util.Set;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service
 * @Description //TODO
 * @Date 16/1/20
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public interface PermissionService extends BaseService<Permission, Long, PermissionDao> {


    List<Permission> getModuleGroups();

    List<Permission> getAllPermission();

    Set<Permission> getChildren(Long permissionId);

}
