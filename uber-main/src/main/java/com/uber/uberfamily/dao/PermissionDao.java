package com.uber.uberfamily.dao;

import com.uber.uberfamily.framework.BaseDao;
import com.uber.uberfamily.framework.MyBatisRepository;
import com.uber.uberfamily.model.Permission;

import java.util.List;
import java.util.Set;

/**
 * @Project uber
 * @Package com.uber.uberfamily.dao
 * @Description //TODO
 * @Date 16/1/20
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@MyBatisRepository
public interface PermissionDao extends BaseDao<Permission, Long> {

    List<Permission> getModuleGroups();

    Set<Permission> getChildren(Long permissionId);
}
